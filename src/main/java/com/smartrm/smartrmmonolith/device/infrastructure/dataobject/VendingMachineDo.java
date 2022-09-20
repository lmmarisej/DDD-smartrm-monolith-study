package com.smartrm.smartrmmonolith.device.infrastructure.dataobject;

/**
 * @author: yoda
 * @description:
 */
public class VendingMachineDo {
    
    private Long machineId;
    private Integer machineType;
    private Integer cabinetDoorState;
    private String iotProductKey;
    private String iotDeviceName;
    private Integer model;

//  private String curUserOpenId;
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public Integer getCabinetDoorState() {
        return cabinetDoorState;
    }
    
    public void setCabinetDoorState(Integer state) {
        this.cabinetDoorState = state;
    }
    
    public Integer getMachineType() {
        return machineType;
    }
    
    public void setMachineType(Integer machineType) {
        this.machineType = machineType;
    }
    
    public String getIotProductKey() {
        return iotProductKey;
    }
    
    public void setIotProductKey(String iotProductKey) {
        this.iotProductKey = iotProductKey;
    }
    
    public String getIotDeviceName() {
        return iotDeviceName;
    }
    
    public void setIotDeviceName(String iotDeviceName) {
        this.iotDeviceName = iotDeviceName;
    }
    
    public Integer getModel() {
        return model;
    }
    
    public void setModel(Integer model) {
        this.model = model;
    }
    
    //  public String getCurUserOpenId() {
//    return curUserOpenId;
//  }
//
//  public void setCurUserOpenId(String curUserOpenId) {
//    this.curUserOpenId = curUserOpenId;
//  }
}
