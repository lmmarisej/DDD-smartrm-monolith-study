package com.smartrm.smartrmmonolith.trade.application.dto;

import com.smartrm.smartrmmonolith.device.domain.InventoryInfo;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public class CabinetLockedNotificationDto {
    
    private Long machineId;
    private List<InventoryInfo> inventorySnapshotWhenOpen;
    private List<InventoryInfo> inventoryWhenLock;
    
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
}
