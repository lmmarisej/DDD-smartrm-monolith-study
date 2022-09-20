package com.smartrm.smartrmmonolith.device.adapter.cloud;

/**
 * @author: yoda
 * @description:
 */
public class IoTPopSuccessEvent {
    
    private int slotId;
    private String orderId;
    
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
