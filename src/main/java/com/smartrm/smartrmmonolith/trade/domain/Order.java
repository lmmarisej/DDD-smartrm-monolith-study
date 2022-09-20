package com.smartrm.smartrmmonolith.trade.domain;

import com.google.common.collect.Lists;
import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.trade.domain.event.OrderCanceledEvent;
import com.smartrm.smartrmmonolith.trade.domain.event.OrderSucceededEvent;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author: yoda
 * @description: 交易上下文订单
 */
public class Order {
    
    private long orderId;
    
    private long machineId;
    
    private long paymentId;
    
    private OrderState state;
    
    private OrderType type;
    
    
    private List<StockedCommodity> commodities;
    private DomainEventBus eventBus;
    
    private Order() {
    
    }

//  public Order(List<StockedCommodity> commodities, DomainEventBus eventBus) {
//    this.commodities = commodities;
//    this.state = OrderState.Start;
//    this.eventBus = eventBus;
//  }
    
    public static Order.Builder Builder() {
        return new Order.Builder();
    }
    
    public List<StockedCommodity> getCommodities() {
        return commodities;
    }
    
    public void cancel() {
        state = OrderState.Canceled;
        eventBus.post(new OrderCanceledEvent(this));
    }
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public long getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }
    
    public OrderState getState() {
        return state;
    }
    
    public OrderType getType() {
        return type;
    }
    
    public void succeed() {
        state = OrderState.Success;
        eventBus.post(new OrderSucceededEvent(this));
    }
    
    public BigDecimal totalAmount() {
        BigDecimal ret = new BigDecimal(0);
        for (StockedCommodity commodity : commodities) {
            ret = ret.add(commodity.getPrice().multiply(new BigDecimal(commodity.getCount())));
        }
        return ret;
    }
    
    public static class Builder {
        
        private long orderId;
        private long machineId;
        private long paymentId;
        private OrderState state;
        private OrderType type;
        private Collection<StockedCommodity> commodities;
        private DomainEventBus eventBus;
        
        public Builder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }
        
        public Builder machineId(long machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public Builder paymentId(long paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        
        public Builder state(OrderState state) {
            this.state = state;
            return this;
        }
        
        public Builder commodities(Collection<StockedCommodity> commodities) {
            this.commodities = Lists.newArrayList(commodities);
            return this;
        }
        
        public Builder type(OrderType type) {
            this.type = type;
            return this;
        }
        
        public Builder eventBus(DomainEventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }
        
        public Order build() {
            if (machineId == 0 || state == null || type == null) {
                throw new DomainException(CommonError.InvalidProperty);
            }
            Order order = new Order();
            order.orderId = this.orderId;
            order.type = this.type;
            order.machineId = this.machineId;
            order.paymentId = this.paymentId;
            order.state = this.state;
            order.commodities = Lists.newArrayList(this.commodities);
            order.eventBus = eventBus;
            return order;
        }
        
        
    }
    
    
}
