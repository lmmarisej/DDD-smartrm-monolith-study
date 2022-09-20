package com.smartrm.smartrmmonolith.payment.adapter.repository.impl;

import com.smartrm.smartrmmonolith.payment.domain.*;
import com.smartrm.smartrmmonolith.payment.domain.repository.PaymentRepository;
import com.smartrm.smartrmmonolith.payment.infrastructure.dataobject.PaymentDo;
import com.smartrm.smartrmmonolith.payment.infrastructure.mapper.PaymentMapper;
import com.smartrm.smartrmmonolith.trade.domain.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author: yoda
 * @description:
 */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    
    @Autowired
    private PaymentMapper mapper;

//  @Autowired
//  private DomainEventBus eventBus;
    
    @Override
    public Payment getByOrderId(Long orderId) {
        PaymentDo paymentDo = mapper.selectByOrderId(orderId);
        if (paymentDo == null) {
            return null;
        }
        Payment payment = Payment.Builder().paymentId(paymentDo.getPaymentId())
                .paymentType(PaymentType.of(paymentDo.getPaymentType()))
                .platformType(PlatformType.of(paymentDo.getPlatformType()))
                .accountInfo(new AccountInfo(paymentDo.getAccountId(), paymentDo.getContractId()))
                .orderInfo(
                        OrderInfo.Builder()
                                .orderId(paymentDo.getOrderId())
                                .type(OrderType.of(paymentDo.getOrderType()))
                                .machineId(paymentDo.getMachineId())
                                .totalAmount(paymentDo.getAmount())
                                .build())
                .lastRetryTime(paymentDo.getLastRetryTime().getTime())
                .retriedTimes(paymentDo.getRetriedTimes())
                .state(PaymentState.of(paymentDo.getState()))
//        .eventBus(eventBus)
                .build();
        return payment;
    }
    
    @Override
    public void add(Payment payment) {
        PaymentDo paymentDo = new PaymentDo();
        paymentDo.setPaymentType(payment.getPaymentType().code());
        paymentDo.setMachineId(payment.getOrder().getMachineId());
        paymentDo.setPaymentId(payment.getPaymentId());
        paymentDo.setOrderId(payment.getOrder().getOrderId());
        paymentDo.setOrderType(payment.getOrder().getType().code());
        paymentDo.setPlatformType(payment.getPlatformType().code());
        paymentDo.setState(payment.getState().code());
        paymentDo.setAmount(payment.getOrder().getTotalAmount());
        paymentDo
                .setAccountId(payment.getAccount() != null ? payment.getAccount().getAccountId() : null);
        paymentDo
                .setContractId(payment.getAccount() != null ? payment.getAccount().getContractId() : null);
        paymentDo.setLastRetryTime(new Timestamp(payment.getLastRetryTime()));
        paymentDo.setRetriedTimes(payment.getRetriedTimes());
        mapper.add(paymentDo);
        payment.setPaymentId(paymentDo.getPaymentId());
    }
    
    public void update(Payment payment) {
        PaymentDo paymentDo = new PaymentDo();
        paymentDo.setPaymentType(payment.getPaymentType().code());
        paymentDo.setMachineId(payment.getOrder().getMachineId());
        paymentDo.setPaymentId(payment.getPaymentId());
        paymentDo.setOrderId(payment.getOrder().getOrderId());
        paymentDo.setOrderType(payment.getOrder().getType().code());
        paymentDo.setPlatformType(payment.getPlatformType().code());
        paymentDo.setState(payment.getState().code());
        paymentDo.setAmount(payment.getOrder().getTotalAmount());
        paymentDo
                .setAccountId(payment.getAccount() != null ? payment.getAccount().getAccountId() : null);
        paymentDo
                .setContractId(payment.getAccount() != null ? payment.getAccount().getContractId() : null);
        paymentDo.setRetriedTimes(payment.getRetriedTimes());
        paymentDo.setLastRetryTime(new Timestamp(payment.getLastRetryTime()));
        mapper.update(paymentDo);
    }
    
}
