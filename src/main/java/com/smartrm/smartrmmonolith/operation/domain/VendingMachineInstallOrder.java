package com.smartrm.smartrmmonolith.operation.domain;

import com.smartrm.smartrmmonolith.device.domain.DeviceModel;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.operation.domain.event.InstallOrderCreatedEvent;

import java.time.LocalDateTime;

/**
 * @author: yoda
 * @description: 售卖机安装订单
 */
public class VendingMachineInstallOrder {
    
    private InstallOrderId orderId;
    private DeviceModel deviceModel;
    private int count;
    private InstallOrderState state;
    private DomainEventBus eventBus;
    private LocalDateTime createdTime;
    private LocalDateTime lastUpdatedTime;
    
    public void onCreated() {
        eventBus.post(new InstallOrderCreatedEvent(this));
    }
    
    public InstallOrderId getOrderId() {
        return orderId;
    }
    
    public void setOrderId(InstallOrderId orderId) {
        this.orderId = orderId;
    }
    
    public DeviceModel getDeviceModel() {
        return deviceModel;
    }
    
    public int getCount() {
        return count;
    }
    
    public InstallOrderState getState() {
        return state;
    }
    
    public DomainEventBus getEventBus() {
        return eventBus;
    }
    
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    
    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }
}
