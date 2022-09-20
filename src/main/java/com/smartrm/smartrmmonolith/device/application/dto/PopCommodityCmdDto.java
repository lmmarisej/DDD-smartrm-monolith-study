package com.smartrm.smartrmmonolith.device.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class PopCommodityCmdDto {
    
    private Long machineId;
    private String commodityId;
    private Long orderId;
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public String getCommodityId() {
        return commodityId;
    }
    
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
}
