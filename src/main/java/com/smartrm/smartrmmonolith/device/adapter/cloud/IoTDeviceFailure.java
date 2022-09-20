package com.smartrm.smartrmmonolith.device.adapter.cloud;

/**
 * @author: yoda
 * @description: iot 设备故障
 */
public class IoTDeviceFailure {
    
    private int errorCode;
    private int slotId;
    private String orderId;
    
    public int getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    
    public int getSlotId() {
        return slotId;
    }
    
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
