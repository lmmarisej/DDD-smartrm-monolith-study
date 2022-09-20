package com.smartrm.smartrmmonolith.payment.application.service.impl;

import com.google.common.collect.Maps;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.infracore.scheduler.ExecutorJobScheduler;
import com.smartrm.smartrmmonolith.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmmonolith.payment.application.dto.PlatformPaymentResultDto;
import com.smartrm.smartrmmonolith.payment.application.dto.PlatformResultCode;
import com.smartrm.smartrmmonolith.payment.application.executor.RefundExecutor;
import com.smartrm.smartrmmonolith.payment.application.remote.PaymentPlatformClient;
import com.smartrm.smartrmmonolith.payment.application.remote.TradeService;
import com.smartrm.smartrmmonolith.payment.application.service.PayService;
import com.smartrm.smartrmmonolith.payment.domain.*;
import com.smartrm.smartrmmonolith.payment.domain.repository.PaymentRepository;
import com.smartrm.smartrmmonolith.payment.infrastructure.PaymentError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: yoda
 * @description: 支付上下文应用层服务实现
 */
@Service
public class PayServiceImpl implements PayService {
    
    private static Logger LOGGER = LoggerFactory.getLogger(PayServiceImpl.class);
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private DomainEventBus eventBus;
    
    @Autowired
    @Qualifier("mockPaymentPlatformClientImpl")
    private PaymentPlatformClient paymentPlatformClient;
    
    @Autowired
    private TradeService tradeService;
    
    @Autowired
    private ExecutorJobScheduler scheduler;
    
    @Override
    public PaymentQrCodeDto startQrCodePayForOrder(PlatformType platformType, OrderInfo order) {
        Payment payment = paymentRepository.getByOrderId(order.getOrderId());
        if (payment == null) {
            payment = Payment.Builder().paymentType(PaymentType.QrCodeScan).orderInfo(order)
                    .platformType(platformType).build();
            paymentRepository.add(payment);
            eventBus.post(new PaymentStartEvent(payment));
        } else if (payment.getState() != PaymentState.Started) {
            LOGGER.error("start a payment with state:" + payment.getState());
            throw new DomainException(PaymentError.PaymentStateNotCorrect);
        }
        //向微信支付平台请求codeUrl
        return paymentPlatformClient.queryQrCode(payment);
    }
    
    @Override
    public void chargeForOrder(OrderInfo order, AccountInfo account) {
        Payment payment = paymentRepository.getByOrderId(order.getOrderId());
        if (payment == null) {
            payment = Payment.Builder().paymentType(PaymentType.AutoDedunction).orderInfo(order)
                    .platformType(PlatformType.Wechat).accountInfo(account).build();
            paymentRepository.add(payment);
            eventBus.post(new PaymentStartEvent(payment));
        } else if (payment.getState() != PaymentState.Started) {
            LOGGER.error("start a auto deduction payment with state:" + payment.getState());
            throw new DomainException(PaymentError.PaymentStateNotCorrect);
        }
        paymentPlatformClient.requestForDeduction(payment);
        PaymentStateChangeEvent event = payment.deductionTried();
        if (event != null) {
            paymentRepository.update(payment);
            eventBus.post(event);
        }
    }
    
    @Override
    public void onPaymentResult(PlatformPaymentResultDto paymentResult) {
        Payment payment = paymentRepository.getByOrderId(paymentResult.getOrderId());
        if (payment == null) {
            LOGGER.error("payment of order not found:" + paymentResult.getOrderId());
            throw new DomainException(PaymentError.PaymentNotExist);
        }
        if (paymentResult.getResultCode() == PlatformResultCode.Success) {
            PaymentStateChangeEvent event = payment.onPaymentSuccess();
            if (event != null) {
                paymentRepository.update(payment);
                /**
                 * 对于扫码支付购物来说，用户在等待商品弹出，
                 * 应先实时调用交易上下文接口通知，再投递事件
                 */
                if (event.getType() == PaymentType.QrCodeScan) {
                    tradeService.onPaymentStateChange(event);
                }
                eventBus.post(event);
            }
        } else if (paymentResult.getResultCode() == PlatformResultCode.Fail) {
            PaymentStateChangeEvent event = payment.onPaymentFailed();
            if (event != null) {
                paymentRepository.update(payment);
                eventBus.post(event);
            }
        }
    }
    
    @Override
    public void onRefundResult(PlatformPaymentResultDto paymentResult) {
        Payment payment = paymentRepository.getByOrderId(paymentResult.getOrderId());
        if (payment == null) {
            LOGGER.error("payment of order not found:" + paymentResult.getOrderId());
            throw new DomainException(PaymentError.PaymentNotExist);
        }
        if (paymentResult.getResultCode() == PlatformResultCode.Success) {
            PaymentStateChangeEvent event = payment.onRefundSuccess();
            if (event != null) {
                paymentRepository.update(payment);
                eventBus.post(event);
            }
        } else if (paymentResult.getResultCode() == PlatformResultCode.Fail) {
            PaymentStateChangeEvent event = payment.onRefundFailed();
            if (event != null) {
                paymentRepository.update(payment);
                eventBus.post(event);
            }
        }
        
    }
    
    @Override
    public void onOrderCanceled(OrderInfo order) {
        Payment payment = paymentRepository.getByOrderId(order.getOrderId());
        if (payment != null) {
            PaymentStateChangeEvent event = payment.onOrderCanceled();
            Map<String, Object> params = Maps.newHashMap();
            params.put("orderId", order.getOrderId());
            scheduler.scheduleRetry(RefundExecutor.class, params, 0, 10000);
            paymentRepository.update(payment);
            if (event != null) {
                eventBus.post(event);
            }
        }
    }
    
}
