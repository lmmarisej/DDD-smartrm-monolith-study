package com.smartrm.smartrmmonolith.device.domain;

import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
public abstract class VendingMachine {
    
    //设备id
    protected long machineId;
    
    //售卖机类型
    protected VendingMachineType type;
    
    //售卖机型号
    protected DeviceModel model;
    
    //库存信息
    protected Map<String, Integer> inventory = new ConcurrentHashMap<>();
    
    public long getMachineId() {
        return machineId;
    }
    
    public VendingMachineType getType() {
        return type;
    }
    
    public List<InventoryInfo> getInventory() {
        return inventory.entrySet().stream()
                .map(entry -> new InventoryInfo(entry.getKey(), entry.getValue())).collect(
                        Collectors.toList());
    }
    
    public void setInventory(List<InventoryInfo> inventoryInfos) {
        inventory.clear();
        inventoryInfos.forEach(info -> inventory.put(info.getCommodityId(), info.getCount()));
    }
    
    public DeviceModel getModel() {
        return model;
    }
    
    public void rollbackInventory(String commodityId, int count) {
        if (inventory.containsKey(commodityId)) {
            inventory.put(commodityId, inventory.get(commodityId) + count);
        } else {
            inventory.put(commodityId, count);
        }
    }
    
    public abstract void onDeviceFailure(DeviceFailureEvent event);
    
}
