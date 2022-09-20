package com.smartrm.smartrmmonolith.payment.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: yoda
 * @description:
 */
public class Payment {
    
    private Logger LOGGER = LoggerFactory.getLogger(Payment.class);
    
    private long paymentId;
    
    private PaymentType paymentType;
    
    private PlatformType platformType;
    
    private OrderInfo order;
    
    private AccountInfo account;
    
    private PaymentState state;
    //已重试次数
    private int retriedTimes;
    //上次重试时间
    private long lastRetryTime;

//  private DomainEventBus eventBus;
    
    private Payment() {
    
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public long getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }
    
    public PlatformType getPlatformType() {
        return platformType;
    }
    
    public OrderInfo getOrder() {
        return order;
    }
    
    public PaymentState getState() {
        return state;
    }
    
    public void setState(PaymentState state) {
        this.state = state;
    }
    
    public PaymentType getPaymentType() {
        return paymentType;
    }
    
    public int getRetriedTimes() {
        return retriedTimes;
    }
    
    public long getLastRetryTime() {
        return lastRetryTime;
    }
    
    public PaymentStateChangeEvent refundTried() {
        lastRetryTime = System.currentTimeMillis();
        if (state != PaymentState.WaitingForRefund) {
            PaymentState pre = state;
            state = PaymentState.WaitingForRefund;
            retriedTimes = 1;
            return new PaymentStateChangeEvent(this, pre);
        } else {
            retriedTimes++;
            return null;
        }
    }
    
    public PaymentStateChangeEvent deductionTried() {
        lastRetryTime = System.currentTimeMillis();
        if (state != PaymentState.WaitingForDeduction) {
            PaymentState pre = state;
            state = PaymentState.WaitingForDeduction;
            retriedTimes = 1;
            return new PaymentStateChangeEvent(this, pre);
        } else {
            retriedTimes++;
            return null;
        }
    }
    
    public PaymentStateChangeEvent onPaymentSuccess() {
        if (state == PaymentState.Success) {
            return null;
        }
        PaymentState pre = this.state;
        if (state == PaymentState.Canceled) {
            state = PaymentState.WaitingForRefund;
        } else {
            state = PaymentState.Success;
        }
        return new PaymentStateChangeEvent(this, pre);
    }
    
    public PaymentStateChangeEvent onOrderCanceled() {
        if (state == PaymentState.WaitingForRefund || state == PaymentState.Canceled) {
            return null;
        }
        PaymentState pre = this.state;
        if (state == PaymentState.Success) {
            state = PaymentState.WaitingForRefund;
        } else {
            state = PaymentState.Canceled;
        }
        return new PaymentStateChangeEvent(this, pre);
        
    }
    
    public PaymentStateChangeEvent onPaymentFailed() {
        if (state == PaymentState.Fail || state == PaymentState.Success) {
            return null;
        }
        PaymentState pre = state;
        state = PaymentState.Fail;
        return new PaymentStateChangeEvent(this, pre);
    }
    
    public PaymentStateChangeEvent onRefundSuccess() {
        if (state == PaymentState.Canceled) {
            return null;
        }
        PaymentState pre = this.state;
        state = PaymentState.RefundSuccess;
        return new PaymentStateChangeEvent(this, pre);
    }
    
    public PaymentStateChangeEvent onRefundFailed() {
        PaymentState pre = this.state;
        state = PaymentState.RefundFailed;
        return new PaymentStateChangeEvent(this, pre);
    }
    
    public AccountInfo getAccount() {
        return account;
    }
    
    public void setAccount(AccountInfo account) {
        this.account = account;
    }
    
    public static class Builder {
        
        private long paymentId;
        private PaymentType paymentType;
        private PlatformType platformType;
        private OrderInfo orderInfo;
        private AccountInfo accountInfo;
        private PaymentState state;
        private int retriedTimes;
        private long lastRetryTime;
//    private DomainEventBus eventBus;
        
        public Builder paymentId(long paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        
        public Builder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }
        
        public Builder platformType(PlatformType platformType) {
            this.platformType = platformType;
            return this;
        }
        
        public Builder orderInfo(OrderInfo orderInfo) {
            this.orderInfo = orderInfo;
            return this;
        }
        
        public Builder accountInfo(AccountInfo accountInfo) {
            this.accountInfo = accountInfo;
            return this;
        }
        
        public Builder state(PaymentState state) {
            this.state = state;
            return this;
        }
        
        public Builder retriedTimes(int retriedTimes) {
            this.retriedTimes = retriedTimes;
            return this;
        }
        
        public Builder lastRetryTime(long lastRetryTime) {
            this.lastRetryTime = lastRetryTime;
            return this;
        }
//
//    public Builder eventBus(DomainEventBus eventBus) {
//      this.eventBus = eventBus;
//      return this;
//    }
        
        public Payment build() {
            Payment ret = new Payment();
            ret.paymentId = this.paymentId;
            ret.paymentType = this.paymentType;
            ret.platformType = this.platformType;
            ret.order = this.orderInfo;
            ret.account = this.accountInfo;
            ret.state = (state == null) ? PaymentState.Started : this.state;
            ret.retriedTimes = this.retriedTimes;
            ret.lastRetryTime = System.currentTimeMillis();
//      ret.eventBus = this.eventBus;
            return ret;
        }
    }
}
