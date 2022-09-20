package com.smartrm.smartrmmonolith.device.infrastructure.dataobject;

/**
 * 售卖机商品库存的DO类，处于基础设施层.
 */
public class VendingMachineInventoryDo {
    
    long machineId;
    String commodityId;
    int count;
    
    public long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    
    public String getCommodityId() {
        return commodityId;
    }
    
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    
}
