package com.smartrm.smartrmmonolith.device.domain;

import com.smartrm.smartrmmonolith.device.infrastructure.DeviceError;
import com.smartrm.smartrmmonolith.device.infrastructure.SimulatorUtil;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public class SlotVendingMachine extends VendingMachine {
    
    private DomainEventBus eventBus;
    
    private SlotVendingMachine() {
    
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public void popCommodity(String commodityId, Long orderId) {
        if (!inventory.containsKey(commodityId) || inventory.get(commodityId) <= 0) {
            throw new DomainException(DeviceError.InventoryNotCorrect);
        }
        //模拟卡货
        if (SimulatorUtil.ifStuck()) {
            DeviceFailure failure = new DeviceFailure();
            failure.setCode(DeviceFailureCode.CommodityStucked);
            DeviceFailureEvent event = new DeviceFailureEvent();
            event.setFailure(failure);
            event.setMachineId(machineId);
            event.setOrderId(orderId);
            event.setCommodityId(commodityId);
            eventBus.post(event);
        }
        int count = inventory.get(commodityId);
        if (count <= 1) {
            inventory.remove(commodityId);
        }
        inventory.put(commodityId, count - 1);
    }
    
    @Override
    public void onDeviceFailure(com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent event) {
    
    }
    
    public static class Builder {
        
        //设备id
        private long machineId;
        //库存信息
        private List<InventoryInfo> inventoryInfo;
        
        private DomainEventBus eventBus;
        
        public Builder machineId(long machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public Builder inventoryInfo(List<InventoryInfo> inventoryInfo) {
            this.inventoryInfo = inventoryInfo;
            return this;
        }
        
        public Builder eventBus(DomainEventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }
        
        public SlotVendingMachine build() {
            SlotVendingMachine ret = new SlotVendingMachine();
            ret.machineId = this.machineId;
            ret.type = VendingMachineType.SLOT;
            for (InventoryInfo info : this.inventoryInfo) {
                ret.inventory.put(info.getCommodityId(), info.getCount());
            }
            ret.eventBus = this.eventBus;
            return ret;
        }
        
    }
    
}
