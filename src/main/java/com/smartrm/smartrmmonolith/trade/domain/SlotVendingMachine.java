package com.smartrm.smartrmmonolith.trade.domain;

import com.google.common.collect.Maps;
import com.smartrm.smartrmmonolith.device.domain.event.PopSuccessEvent;
import com.smartrm.smartrmmonolith.infracore.aggregate.AggregateBase;
import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.infracore.idgenerator.UniqueIdGeneratorUtil;
import com.smartrm.smartrmmonolith.payment.domain.PlatformType;
import com.smartrm.smartrmmonolith.trade.domain.event.OrderCreatedEvent;
import com.smartrm.smartrmmonolith.trade.domain.remote.*;
import com.smartrm.smartrmmonolith.trade.infrastructure.TradeError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description: 交易上下文货道售卖机
 */
public class SlotVendingMachine extends AggregateBase {
    
    private static Logger LOGGER = LoggerFactory.getLogger(SlotVendingMachine.class);
    
    private long machineId;
    private SlotVendingMachineState state;
    private Order curOrder;
    
    private SlotVendingMachine() {
    
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public SlotVendingMachineState getState() {
        return state;
    }
    
    public Order getCurOrder() {
        return curOrder;
    }
    
    public VendingMachineCommodityList getCommodityList(TradeDeviceService deviceService,
                                                        TradeCommodityService commodityService) {
        List<InventoryInfo> inventoryInfos = deviceService.getInventory(machineId);
        List<CommodityInfo> commodityInfos = commodityService.getCommodityList(
                inventoryInfos.stream().map(InventoryInfo::getCommodityId).collect(Collectors.toList()));
        Map<String, Integer> inventoryMap = Maps.newHashMap();
        inventoryInfos.forEach(info -> inventoryMap.put(info.getCommodityId(), info.getCount()));
        List<StockedCommodity> commodities = commodityInfos.stream()
                .map(info -> new StockedCommodity(
                        info.getCommodityId(),
                        info.getCommodityName(),
                        info.getImageUrl(),
                        info.getPrice(),
                        inventoryMap.get(info.getCommodityId()))).collect(Collectors.toList());
        return new VendingMachineCommodityList(machineId, commodities);
    }
    
    private boolean checkInventory(Collection<StockedCommodity> commodities,
                                   TradeDeviceService deviceService) {
        List<InventoryInfo> inventoryInfos = deviceService.getInventory(machineId);
        Map<String, Integer> inventoryMap = Maps.newHashMap();
        inventoryInfos.forEach(info -> inventoryMap.put(info.getCommodityId(), info.getCount()));
        for (StockedCommodity stocked : commodities) {
            Integer count = inventoryMap.get(stocked.getCommodityId());
            if (count == null || count < stocked.getCount()) {
                return false;
            }
        }
        return true;
    }
    
    public PaymentQrCode selectCommodity(Collection<StockedCommodity> commodities,
                                         TradeDeviceService deviceService, TradePayService payService, PlatformType platformType) {
        //校验设备状态
        if (state != SlotVendingMachineState.Ready) {
            throw new DomainException(TradeError.VendingMachineStateNotRight);
        }
        //校验库存
        if (!checkInventory(commodities, deviceService)) {
            throw new DomainException(TradeError.InventoryCheckFail);
        }
        curOrder = this.generateOrder(commodities);
        emitEvent(new OrderCreatedEvent(this.machineId, curOrder));
        state = SlotVendingMachineState.Trading;
        PaymentQrCode ret = payService.startQrCodePayForOrder(platformType, curOrder);
        curOrder.setPaymentId(ret.getPaymentId());
        incVersion();
        return ret;
    }
    
    private Order generateOrder(Collection<StockedCommodity> commodities) {
        return Order.Builder().commodities(commodities)
                .orderId(UniqueIdGeneratorUtil.instance().nextId())
                .state(OrderState.Start)
                .type(OrderType.SlotQrScanePaid)
                .machineId(this.machineId)
                .eventBus(eventBus)
                .build();
    }
    
    public void finishOrder(long orderId, TradeDeviceService deviceService) throws Exception {
        if (this.curOrder == null || this.curOrder.getOrderId() != orderId) {
            LOGGER.warn("order finished when slot vending machine has release it:{},{}", machineId,
                    orderId);
            return;
        }
        //弹出商品
        if (curOrder.getCommodities().size() > 1) {
            throw new DomainException(CommonError.UnExpected)
                    .withMsg("slot vending machine only support one commodity order");
        }
        for (StockedCommodity commodity : curOrder.getCommodities()) {
            deviceService.popCommodity(curOrder.getMachineId(), commodity.getCommodityId(), curOrder.getOrderId());
        }
        this.curOrder.succeed();
        this.state = SlotVendingMachineState.Popping;
        incVersion();
    }
    
    public void cancelOrder() {
        if (curOrder == null || curOrder.getState() == OrderState.Canceled) {
            LOGGER.warn("cancel order state not right.");
            return;
        }
        curOrder.cancel();
        state = SlotVendingMachineState.Ready;
//    curOrder = null;
        incVersion();
    }
    
    public void onPopSuccess(PopSuccessEvent event) {
        if (state == SlotVendingMachineState.Popping && curOrder != null
                && curOrder.getOrderId() == event.getOrderId()) {
            ready();
        }
    }
    
    public void ready() {
        if (state == SlotVendingMachineState.Ready) {
            return;
        }
        state = SlotVendingMachineState.Ready;
        incVersion();
    }
    
    public static class Builder {
        private long machineId;
        private SlotVendingMachineState state;
        private Order curOrder;
        private long version;
        private DomainEventBus eventBus;
        
        public Builder machineId(long machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public Builder state(SlotVendingMachineState state) {
            this.state = state;
            return this;
        }
        
        public Builder curOrder(Order curOrder) {
            this.curOrder = curOrder;
            return this;
        }
        
        public Builder eventBus(DomainEventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }
        
        public Builder version(long version) {
            this.version = version;
            return this;
        }
        
        public SlotVendingMachine build() {
            if (this.eventBus == null) {
                throw new DomainException(CommonError.UnExpected);
            }
            SlotVendingMachine machine = new SlotVendingMachine();
            machine.curOrder = this.curOrder;
            machine.machineId = this.machineId;
            machine.state = this.state;
            machine.setEventBus(this.eventBus);
            machine.version = this.version;
            return machine;
        }
        
    }
    
}
