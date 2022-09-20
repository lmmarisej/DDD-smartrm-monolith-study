package com.smartrm.smartrmmonolith.device.domain.cabinet;

import com.smartrm.smartrmmonolith.device.domain.InventoryInfo;
import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;

import java.util.List;

/**
 * @author: yoda
 * @description: 货柜机柜门锁定事件
 */
public class CabinetVendingMachineLockedEvent extends DomainEvent {
    
    private Long machineId;
    //  private String userOpenId;
    private List<InventoryInfo> inventorySnapshotWhenOpen;
    private List<InventoryInfo> inventoryWhenLock;
    
    public CabinetVendingMachineLockedEvent() {
        super("device.CabinetVendingMachineLockedEvent");
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public List<InventoryInfo> getInventorySnapshotWhenOpen() {
        return inventorySnapshotWhenOpen;
    }
    
    public void setInventorySnapshotWhenOpen(
            List<InventoryInfo> inventorySnapshotWhenOpen) {
        this.inventorySnapshotWhenOpen = inventorySnapshotWhenOpen;
    }
    
    public List<InventoryInfo> getInventoryWhenLock() {
        return inventoryWhenLock;
    }
    
    public void setInventoryWhenLock(
            List<InventoryInfo> inventoryWhenLock) {
        this.inventoryWhenLock = inventoryWhenLock;
    }
    
    @Override
    public String key() {
        return machineId.toString();
    }

//
//  public String getUserOpenId() {
//    return userOpenId;
//  }
//
//  public void setUserOpenId(String userOpenId) {
//    this.userOpenId = userOpenId;
//  }
}
