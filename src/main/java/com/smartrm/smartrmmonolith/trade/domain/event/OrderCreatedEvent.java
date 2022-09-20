package com.smartrm.smartrmmonolith.trade.domain.event;

import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;
import com.smartrm.smartrmmonolith.trade.domain.Order;
import com.smartrm.smartrmmonolith.trade.domain.OrderType;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
public class OrderCreatedEvent extends DomainEvent {
    
    private long machineId;
    private long orderId;
    private OrderType orderType;
    private BigDecimal totalAmount;
    
    public OrderCreatedEvent(long machineId, Order order) {
        super("trade.OrderCreatedEvent");
        this.machineId = machineId;
        this.orderId = order.getOrderId();
        this.orderType = order.getType();
        this.totalAmount = order.totalAmount();
    }
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public OrderType getOrderType() {
        return orderType;
    }
    
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    @Override
    public String key() {
        return Long.toString(machineId);
    }
}
