package com.smartrm.smartrmmonolith.payment.domain;

import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class PaymentStateChangeEvent extends DomainEvent {
    
    private Long paymentId;
    private PaymentType type;
    private OrderInfo orderInfo;
    private PaymentState preState;
    private PaymentState curState;
    private Long time;
    
    public PaymentStateChangeEvent(Payment payment, PaymentState preState) {
        super("payment.PaymentStateChangeEvent");
        this.paymentId = payment.getPaymentId();
        this.type = payment.getPaymentType();
        this.orderInfo = payment.getOrder();
        this.curState = payment.getState();
        this.preState = preState;
        time = System.currentTimeMillis();
    }
    
    public Long getPaymentId() {
        return paymentId;
    }
    
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }
    
    public PaymentState getPreState() {
        return preState;
    }
    
    public PaymentState getCurState() {
        return curState;
    }
    
    public PaymentType getType() {
        return type;
    }
    
    @Override
    public String key() {
        return paymentId.toString();
    }
}
