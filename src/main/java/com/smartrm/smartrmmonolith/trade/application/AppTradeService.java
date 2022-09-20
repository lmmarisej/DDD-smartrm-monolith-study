package com.smartrm.smartrmmonolith.trade.application;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.smartrm.smartrmmonolith.device.domain.VendingMachineType;
import com.smartrm.smartrmmonolith.device.domain.event.CabinetVendingMachineLockedEvent;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.infracore.scheduler.ExecutorJobScheduler;
import com.smartrm.smartrmmonolith.payment.domain.OrderInfo;
import com.smartrm.smartrmmonolith.payment.domain.PaymentState;
import com.smartrm.smartrmmonolith.payment.domain.PaymentStateChangeEvent;
import com.smartrm.smartrmmonolith.trade.application.dto.SelectCommodityCmdDto;
import com.smartrm.smartrmmonolith.trade.application.dto.VendingMachineCommodityListDto;
import com.smartrm.smartrmmonolith.trade.application.executor.DeviceFailureExecutor;
import com.smartrm.smartrmmonolith.trade.application.executor.PoppingExpireExecutor;
import com.smartrm.smartrmmonolith.trade.application.executor.TradeExpireExecutor;
import com.smartrm.smartrmmonolith.trade.domain.*;
import com.smartrm.smartrmmonolith.trade.domain.event.OrderCreatedEvent;
import com.smartrm.smartrmmonolith.trade.domain.remote.*;
import com.smartrm.smartrmmonolith.trade.domain.repository.OrderRepository;
import com.smartrm.smartrmmonolith.trade.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmmonolith.trade.infrastructure.TradeError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author: yoda
 * @description: 应用层交易服务
 */
@Service
public class AppTradeService {
    
    private static Logger LOGGER = LoggerFactory.getLogger(AppTradeService.class);
    
    private static int DEVICE_FAILURE_PROCESS_DELAY = 1000;
    
    private static int TRADE_EXPIRE_SECS = 30;
    
    @Autowired
    TradePayService payService;
    
    @Autowired
    TradeUserService userService;
    
    @Autowired
    TradeDeviceService deviceService;
    
    @Autowired
    TradeCommodityService commodityService;
    
    @Autowired
    VendingMachineRepository machineRepository;
    
    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    DomainEventBus eventBus;
    
    @Autowired
    ExecutorJobScheduler scheduler;
    
    public VendingMachineCommodityListDto queryCommodityList(long machineId) {
        SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(machineId);
        if (machine == null) {
            LOGGER.warn("vending machine not found:{}", machineId);
            throw new DomainException(TradeError.VendingMachineNotFound);
        }
        return new VendingMachineCommodityListDto(
                machine.getCommodityList(deviceService, commodityService), machine.getState());
    }
    
    @Transactional
    public PaymentQrCode selectCommodity(SelectCommodityCmdDto cmd) {
        SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(cmd.getMachineId());
        if (machine == null) {
            LOGGER.warn("vending machine not found:{}", cmd.getMachineId());
            throw new DomainException(TradeError.VendingMachineNotFound);
        }
        CommodityInfo commodityInfo = commodityService.getCommodityDetail(cmd.getCommodityId());
        if (commodityInfo == null) {
            LOGGER.warn("commodity not exist:{}", cmd.getCommodityId());
            throw new DomainException(TradeError.CommodityNotExist);
        }
        StockedCommodity commodity = new StockedCommodity(
                commodityInfo.getCommodityId(),
                commodityInfo.getCommodityName(),
                commodityInfo.getImageUrl(),
                commodityInfo.getPrice(),
                1
        );
        PaymentQrCode code = machine
                .selectCommodity(Lists.newArrayList(commodity), deviceService, payService,
                        cmd.getPlatformType());
        Map<String, Object> params = Maps.newHashMap();
        params.put("orderId", machine.getCurOrder().getOrderId());
        params.put("machineId", machine.getMachineId());
        scheduler.scheduleRetry(TradeExpireExecutor.class, params, 30 * 1000, 1000);
        return code;
    }
    
    @Transactional
    public void onPaymentStateChange(PaymentStateChangeEvent event) {
        OrderInfo orderInfo = event.getOrderInfo();
        if (event.getCurState() == PaymentState.Success) {
            if (event.getOrderInfo().getType() == OrderType.SlotQrScanePaid) {
                // 如果是货道售卖机，调用货道售卖机聚合
                SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(orderInfo.getMachineId());
                try {
                    machine.finishOrder(orderInfo.getOrderId(), deviceService);
                } catch (Exception e) {
                    //如果完成订单失败，则取消订单，退还货款
                    LOGGER.error("fail to finish order.", e);
                    machine.cancelOrder();
                }
                //弹出商品超时任务
                Map<String, Object> params = Maps.newHashMap();
                params.put("orderId", event.getOrderInfo().getOrderId());
                params.put("machineId", machine.getMachineId());
                scheduler.scheduleRetry(PoppingExpireExecutor.class, params, 10 * 1000, 1000);
            } else {
                // 如果是货柜机，直接调用货柜机实体
                Order order = orderRepository.getOrderById(event.getOrderInfo().getOrderId());
                order.succeed();
            }
        }
    }
    
    @PreAuthorize("hasAuthority('OpenCabinet')")
    @Transactional
    public void openCabinetVendingMachine(long machineId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserOpenId = (String) authentication.getPrincipal();
        CabinetVendingMachine cabinet = machineRepository.getCabinetVendingMachineById(machineId);
        cabinet.open(curUserOpenId, deviceService);
//    machineRepository.updateCabinetVendingMachine(cabinet);
    }
    
    /**
     * 柜机的关门锁定事件触发订单结束。
     */
    @Transactional
    public void onCabinetLocked(CabinetVendingMachineLockedEvent event) {
        CabinetVendingMachine cabinet = machineRepository.getCabinetVendingMachineById(event.getMachineId());
        try {
            Order order = cabinet.onLocked(event, commodityService);
            orderRepository.addOrder(order);
//    machineRepository.updateCabinetVendingMachine(cabinet);
            UserInfo userInfo = userService.getUserInfo(cabinet.getCurUserOpenId());
            // 免密支付
            payService.chargeForOrder(
                    OrderInfo.Builder()
                            .machineId(order.getMachineId())
                            .orderId(order.getOrderId())
                            .type(order.getType())
                            .totalAmount(order.totalAmount())       // 在这里进行扩展新用户优惠
                            .build(),
                    userInfo
            );
            eventBus.post(new OrderCreatedEvent(cabinet.getMachineId(), order));
        } catch (DomainException e) {
            if (e.getErrCode() != TradeError.OrderAmountZero) {
                throw e;
            }
        }
    }
    
    public void onDeviceFailure(DeviceFailureEvent event) {
        if (event.getMachineType() == VendingMachineType.SLOT) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("event", event);
            scheduler.scheduleRetry(DeviceFailureExecutor.class, params, 0, 1000);
        }
    }
    
}
