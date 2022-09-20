package com.smartrm.smartrmmonolith.trade.domain.repository;

import com.smartrm.smartrmmonolith.trade.domain.CabinetVendingMachine;
import com.smartrm.smartrmmonolith.trade.domain.SlotVendingMachine;

/**
 * @author: yoda
 * @description:
 */
public interface VendingMachineRepository {
    
    SlotVendingMachine getSlotVendingMachineById(long id);
    
    CabinetVendingMachine getCabinetVendingMachineById(long id);
    
    void updateSlotVendingMachine(SlotVendingMachine machine);
    
    void updateCabinetVendingMachine(CabinetVendingMachine machine);
    
}
