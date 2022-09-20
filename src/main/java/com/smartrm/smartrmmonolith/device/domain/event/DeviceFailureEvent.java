package com.smartrm.smartrmmonolith.device.domain.event;

import com.smartrm.smartrmmonolith.device.domain.VendingMachineType;
import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;

/**
 * @author: yoda
 * @description: 设备故障事件
 */
public class DeviceFailureEvent extends DomainEvent {
    
    private Long machineId;
    private VendingMachineType machineType;
    private DeviceFailure failure;
    private Long orderId;
    
    public DeviceFailureEvent() {
        super("device.DeviceFailureEvent");
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public DeviceFailure getFailure() {
        return failure;
    }
    
    public void setFailure(DeviceFailure failure) {
        this.failure = failure;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public VendingMachineType getMachineType() {
        return machineType;
    }
    
    public void setMachineType(VendingMachineType machineType) {
        this.machineType = machineType;
    }
    
    @Override
    public String key() {
        return machineId.toString();
    }
}
