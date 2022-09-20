package com.smartrm.smartrmmonolith.device.domain.cabinet;

import com.smartrm.smartrmmonolith.device.domain.InventoryInfo;
import com.smartrm.smartrmmonolith.device.domain.VendingMachine;
import com.smartrm.smartrmmonolith.device.domain.VendingMachineType;
import com.smartrm.smartrmmonolith.device.domain.event.CabinetVendingMachineLockedEvent;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.device.infrastructure.DeviceError;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;

import java.util.List;

/**
 * @author: yoda
 * @description: 货柜机
 */
public class CabinetVendingMachine extends VendingMachine {
    
    //打开时的库存快照
    private List<InventoryInfo> inventorySnapshotWhenOpen;
    //当前状态
    private CabinetDoorState doorState;
//  //当前交易用户openId
//  private String curUserOpenId;
    
    private DomainEventBus eventBus;
    
    private CabinetVendingMachine() {
    
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public List<InventoryInfo> getInventorySnapshotWhenOpen() {
        return inventorySnapshotWhenOpen;
    }
    
    public CabinetDoorState getDoorState() {
        return doorState;
    }
    
    public void onOpen(List<InventoryInfo> inventory) {
        inventorySnapshotWhenOpen = inventory;
        doorState = CabinetDoorState.Open;
    }
//
//  public String getCurUserOpenId() {
//    return curUserOpenId;
//  }
//
//  public void setCurUserOpenId(String curUserOpenId) {
//    this.curUserOpenId = curUserOpenId;
//  }
    
    public void onLocked(List<InventoryInfo> inventory) {
        if (doorState != CabinetDoorState.Open) {
            throw new DomainException(DeviceError.CabinetStateNotRight);
        }
        doorState = CabinetDoorState.Locked;
        this.setInventory(inventory);
        CabinetVendingMachineLockedEvent event = new CabinetVendingMachineLockedEvent();
        event.setMachineId(this.getMachineId());
        event.setInventorySnapshotWhenOpen(this.inventorySnapshotWhenOpen);
        event.setInventoryWhenLock(this.getInventory());
//    event.setUserOpenId(this.curUserOpenId);
        eventBus.post(event);
    }
    
    @Override
    public void onDeviceFailure(DeviceFailureEvent event) {
    
    }
    
    public static class Builder {
        
        //设备id
        private long machineId;
        //库存信息
        private List<InventoryInfo> inventoryInfo;
        //打开时的库存快照
        private List<InventoryInfo> inventoryWhenOpen;
        //当前状态
        private CabinetDoorState state;

//    private String curUserOpenId;
        
        private DomainEventBus eventBus;
        
        public Builder machineId(long machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public Builder inventoryInfo(List<InventoryInfo> inventoryInfo) {
            this.inventoryInfo = inventoryInfo;
            return this;
        }
        
        public Builder inventoryWhenOpen(List<InventoryInfo> inventoryInfo) {
            this.inventoryWhenOpen = inventoryInfo;
            return this;
        }
        
        public Builder state(CabinetDoorState state) {
            this.state = state;
            return this;
        }
//
//    public Builder curUserOpenId(String curUserOpenId) {
//      this.curUserOpenId = curUserOpenId;
//      return this;
//    }
        
        public Builder eventBus(DomainEventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }
        
        public CabinetVendingMachine build() {
            CabinetVendingMachine ret = new CabinetVendingMachine();
            ret.machineId = this.machineId;
            ret.type = VendingMachineType.CABINET;
            ret.setInventory(this.inventoryInfo);
            if (this.state == null) {
                ret.doorState = CabinetDoorState.Locked;
            } else if (this.state == CabinetDoorState.Open) {
                ret.inventorySnapshotWhenOpen = this.inventoryWhenOpen;
//        ret.curUserOpenId = this.curUserOpenId;
                ret.doorState = this.state;
            } else {
                ret.doorState = this.state;
            }
            ret.eventBus = this.eventBus;
            return ret;
        }
    }
    
}
