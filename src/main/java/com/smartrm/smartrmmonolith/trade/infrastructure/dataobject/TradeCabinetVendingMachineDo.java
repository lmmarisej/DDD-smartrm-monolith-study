package com.smartrm.smartrmmonolith.trade.infrastructure.dataobject;

/**
 * @author: yoda
 * @description:
 */
public class TradeCabinetVendingMachineDo {
    
    private long machineId;
    private int state;
    private String curUserOpenId;
    
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
    
    public String getCurUserOpenId() {
        return curUserOpenId;
    }
    
    public void setCurUserOpenId(String curUserOpenId) {
        this.curUserOpenId = curUserOpenId;
    }
}
