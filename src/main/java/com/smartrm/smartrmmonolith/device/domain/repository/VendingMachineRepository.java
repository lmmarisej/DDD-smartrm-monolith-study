package com.smartrm.smartrmmonolith.device.domain.repository;

import com.smartrm.smartrmmonolith.device.domain.VendingMachine;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetVendingMachine;
import com.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceId;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotVendingMachine;

/**
 * @author: yoda
 * @description:
 */
public interface VendingMachineRepository {
    
    VendingMachine getVendingMachine(long machineId);
    
    SlotVendingMachine getSlotVendingMachineById(long machineId);
    
    SlotVendingMachine getSlotVendingMachineByDeviceId(IoTDeviceId deviceId);
    
    CabinetVendingMachine getCabinetVendingMachineById(long machineId);
    
    void add(VendingMachine vendingMachine);
    
    void updateInventory(VendingMachine machine);
    
    void updateCabinetVendingMachineState(CabinetVendingMachine machine);
    
    void clearInventorySnapshot(long machineId);
    
    void saveInventorySnapshot(CabinetVendingMachine machine);
    
}
