package com.smartrm.smartrmmonolith.device.adapter.simulator.controller;

import com.smartrm.smartrmmonolith.device.application.dto.PopCommodityCmdDto;
import com.smartrm.smartrmmonolith.device.application.dto.VendingMachineDto;
import com.smartrm.smartrmmonolith.device.application.service.VendingMachineDeviceService;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetVendingMachine;
import com.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/mock/device")
public class DeviceSimulatorController {
    
    @Autowired
    private VendingMachineDeviceService deviceService;
    
    @RequestMapping(value = "/open/{machineId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<VendingMachineDto> openCabinetVendingMachine(@PathVariable Long machineId) {
        CabinetVendingMachine machine = deviceService.openCabinetVendingMachine(machineId);
        VendingMachineDto vendingMachineDto = new VendingMachineDto();
        vendingMachineDto.setMachineId(machineId);
        vendingMachineDto.setState(machine.getDoorState());
        vendingMachineDto.setInventory(deviceService.getInventory(machineId));
        return CommonResponse.success(vendingMachineDto);
    }
    
    @RequestMapping(value = "/lock/{machineId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<VendingMachineDto> lockCabinetVendingMachine(@PathVariable Long machineId,
                                                                       @RequestBody VendingMachineDto vendingMachineDto) {
        CabinetVendingMachine machine = deviceService
                .onLockCabinetVendingMachine(machineId, vendingMachineDto.getInventory());
        vendingMachineDto.setMachineId(machineId);
        vendingMachineDto.setState(machine.getDoorState());
        vendingMachineDto.setInventory(machine.getInventory());
        return CommonResponse.success(vendingMachineDto);
    }
    
    @RequestMapping(value = "/inventory/{machineId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<VendingMachineDto> getInventory(@PathVariable Long machineId) {
        VendingMachineDto vo = new VendingMachineDto();
        vo.setMachineId(machineId);
        vo.setInventory(deviceService.getInventory(machineId));
        return CommonResponse.success(vo);
    }
    
    @RequestMapping(value = "/pop", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<VendingMachineDto> popCommodity(@RequestBody PopCommodityCmdDto cmd) {
        deviceService.popCommodity(cmd);
        return CommonResponse.success();
    }
}
