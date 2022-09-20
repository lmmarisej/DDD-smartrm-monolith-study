package com.smartrm.smartrmmonolith.trade.domain.event;

import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;
import com.smartrm.smartrmmonolith.trade.domain.Order;
import com.smartrm.smartrmmonolith.trade.domain.OrderType;
import com.smartrm.smartrmmonolith.trade.domain.StockedCommodity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: yoda
 * @description: 订单完成事件
 */
public class OrderSucceededEvent extends DomainEvent {
    
    private long machineId;
    private long orderId;
    private OrderType type;
    private BigDecimal totalAmount;
    private List<StockedCommodity> commodities;
    
    public OrderSucceededEvent(Order order) {
        super("trade.OrderSucceededEvent");
        machineId = order.getMachineId();
        orderId = order.getOrderId();
        type = order.getType();
        totalAmount = order.totalAmount();
        commodities = order.getCommodities();
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public OrderType getType() {
        return type;
    }
    
    public void setType(OrderType type) {
        this.type = type;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public List<StockedCommodity> getCommodities() {
        return commodities;
    }
    
    public void setCommodities(
            List<StockedCommodity> commodities) {
        this.commodities = commodities;
    }
    
    @Override
    public String key() {
        return Long.toString(machineId);
    }
}
