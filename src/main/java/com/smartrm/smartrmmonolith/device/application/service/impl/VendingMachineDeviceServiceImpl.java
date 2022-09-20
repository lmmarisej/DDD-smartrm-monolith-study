package com.smartrm.smartrmmonolith.device.application.service.impl;

import com.smartrm.smartrmmonolith.device.application.dto.PopCommodityCmdDto;
import com.smartrm.smartrmmonolith.device.application.service.VendingMachineDeviceService;
import com.smartrm.smartrmmonolith.device.domain.InventoryInfo;
import com.smartrm.smartrmmonolith.device.domain.VendingMachine;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetDoorState;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetVendingMachine;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.device.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotCommodityService;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotVendingMachine;
import com.smartrm.smartrmmonolith.device.infrastructure.DeviceError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
@Service
public class VendingMachineDeviceServiceImpl implements VendingMachineDeviceService {
    
    private final static Logger LOGGER = LoggerFactory
            .getLogger(VendingMachineDeviceServiceImpl.class);
    @Autowired
    private VendingMachineRepository vendingMachineRepository;
    
    @Autowired
    private SlotCommodityService slotCommodityService;
    
    @Override
    public List<InventoryInfo> getInventory(long machineId) {
        VendingMachine machine = vendingMachineRepository.getVendingMachine(machineId);
        if (machine == null) {
            return null;
        }
        return machine.getInventory();
    }
    
    @Override
    public SlotVendingMachine popCommodity(PopCommodityCmdDto cmd) {
        SlotVendingMachine slotVendingMachine = vendingMachineRepository
                .getSlotVendingMachineById(cmd.getMachineId());
        if (slotVendingMachine == null) {
            throw new DomainException(DeviceError.MachineNotExist);
        }
        try {
            slotVendingMachine.popCommodity(cmd.getCommodityId(), cmd.getOrderId());
        } catch (Exception e) {
            LOGGER.error("error when pop commodity.", e);
            throw new DomainException(DeviceError.FailToPopCommodity).withMsg(e.getMessage());
        }
        vendingMachineRepository.updateInventory(slotVendingMachine);
        return slotVendingMachine;
    }
    
    //  @PreAuthorize("hasAuthority('OpenCabinet')")
    @Transactional
    @Override
    public CabinetVendingMachine openCabinetVendingMachine(long machineId) {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String curUserOpenId = (String) authentication.getPrincipal();
        CabinetVendingMachine machine = vendingMachineRepository
                .getCabinetVendingMachineById(machineId);
        if (machine == null) {
            throw new DomainException(DeviceError.MachineNotExist);
        }
        if (machine.getDoorState() != CabinetDoorState.Locked) {
            throw new DomainException(DeviceError.CabinetStateNotRight);
        }
//    machine.setCurUserOpenId(curUserOpenId);
        machine.onOpen(machine.getInventory());
        vendingMachineRepository.updateCabinetVendingMachineState(machine);
        vendingMachineRepository.saveInventorySnapshot(machine);
        return machine;
    }
    
    @Transactional
    @Override
    public CabinetVendingMachine onLockCabinetVendingMachine(long machineId,
                                                             List<InventoryInfo> inventory) {
        CabinetVendingMachine machine = vendingMachineRepository
                .getCabinetVendingMachineById(machineId);
        if (machine == null) {
            throw new DomainException(DeviceError.MachineNotExist);
        }
        machine.onLocked(inventory);
        vendingMachineRepository.updateCabinetVendingMachineState(machine);
        vendingMachineRepository.clearInventorySnapshot(machineId);
        vendingMachineRepository.updateInventory(machine);
        return machine;
    }
    
    @Transactional
    @Override
    public void onDeviceFailure(DeviceFailureEvent event) {
        VendingMachine machine = vendingMachineRepository.getVendingMachine(event.getMachineId());
        
    }
    
}
