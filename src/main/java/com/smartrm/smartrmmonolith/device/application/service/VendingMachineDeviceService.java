package com.smartrm.smartrmmonolith.device.application.service;

import com.smartrm.smartrmmonolith.device.application.dto.PopCommodityCmdDto;
import com.smartrm.smartrmmonolith.device.domain.InventoryInfo;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetVendingMachine;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotVendingMachine;

import java.util.List;

/**
 * @author: yoda
 * @description: 售卖机设备上下文应用层服务
 */
public interface VendingMachineDeviceService {
    
    /**
     * 获取售卖机商品库存
     *
     * @param machineId 售卖机id
     * @return 库存信息
     */
    List<InventoryInfo> getInventory(long machineId);
    
    /**
     * 弹出商品
     *
     * @param cmd 命令对象
     * @return 货道售卖机领域实体对象
     */
    SlotVendingMachine popCommodity(PopCommodityCmdDto cmd);
    
    /**
     * 打开货柜机
     *
     * @param machineId 售卖机id
     * @return 货柜机领域实体对象
     */
    CabinetVendingMachine openCabinetVendingMachine(long machineId);
    
    /**
     * 货柜机柜门锁定事件处理。用户关门后，接收由售卖机设备通知过来的锁定事件并进行相应处理
     *
     * @param machineId 售卖机id
     * @param inventory 售卖机设备同步过来的商品库存信息
     * @return 货柜机领域实体对象
     */
    CabinetVendingMachine onLockCabinetVendingMachine(long machineId, List<InventoryInfo> inventory);
    
    void onDeviceFailure(DeviceFailureEvent event);
    
}
