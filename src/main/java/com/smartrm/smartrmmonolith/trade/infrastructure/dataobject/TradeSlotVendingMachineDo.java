package com.smartrm.smartrmmonolith.trade.infrastructure.dataobject;

/**
 * @author: yoda
 * @description:
 */
public class TradeSlotVendingMachineDo {
    
    private long machineId;
    private int state;
    private long curOrderId;
    private long version;
    
    public long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public long getCurOrderId() {
        return curOrderId;
    }
    
    public void setCurOrderId(long curOrderId) {
        this.curOrderId = curOrderId;
    }
    
    public long getVersion() {
        return version;
    }
    
    public void setVersion(long version) {
        this.version = version;
    }
}
