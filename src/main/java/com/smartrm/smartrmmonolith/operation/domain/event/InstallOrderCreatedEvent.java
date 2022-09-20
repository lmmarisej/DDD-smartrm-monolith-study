package com.smartrm.smartrmmonolith.operation.domain.event;

import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;
import com.smartrm.smartrmmonolith.operation.domain.VendingMachineInstallOrder;

/**
 * @author: yoda
 * @description:
 */
public class InstallOrderCreatedEvent extends DomainEvent {
    
    private String installOrderId;
    private int deviceModel;
    private int count;
    
    public InstallOrderCreatedEvent() {
        super("operation.InstallOrderCreatedEvent");
    }
    
    public InstallOrderCreatedEvent(VendingMachineInstallOrder order) {
        super("operation.InstallOrderCreatedEvent");
        installOrderId = order.getOrderId().id();
        deviceModel = order.getDeviceModel().code();
        count = order.getCount();
    }
    
    public String getInstallOrderId() {
        return installOrderId;
    }
    
    public void setInstallOrderId(String installOrderId) {
        this.installOrderId = installOrderId;
    }
    
    public int getDeviceModel() {
        return deviceModel;
    }
    
    public void setDeviceModel(int deviceModel) {
        this.deviceModel = deviceModel;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public String key() {
        return installOrderId;
    }
}
