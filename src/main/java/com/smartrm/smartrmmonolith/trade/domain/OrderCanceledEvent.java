package com.smartrm.smartrmmonolith.trade.domain;

import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:订单取消事件
 */
public class OrderCanceledEvent extends DomainEvent {
    
    private long machineId;
    private long orderId;
    private OrderType type;
    private BigDecimal totalAmount;
    
    public OrderCanceledEvent(Order order) {
        super("trade.OrderCanceledEvent");
        machineId = order.getMachineId();
        orderId = order.getOrderId();
        type = order.getType();
        totalAmount = order.totalAmount();
    }
    
    public long getOrderId() {
        return orderId;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public OrderType getType() {
        return type;
    }
    
    public long getMachineId() {
        return machineId;
    }
}
