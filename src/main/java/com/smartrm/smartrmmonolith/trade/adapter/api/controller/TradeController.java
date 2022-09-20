package com.smartrm.smartrmmonolith.trade.adapter.api.controller;

import com.smartrm.smartrmmonolith.device.domain.event.CabinetVendingMachineLockedEvent;
import com.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import com.smartrm.smartrmmonolith.trade.application.AppTradeService;
import com.smartrm.smartrmmonolith.trade.application.dto.CabinetLockedNotificationDto;
import com.smartrm.smartrmmonolith.trade.application.dto.SelectCommodityCmdDto;
import com.smartrm.smartrmmonolith.trade.application.dto.VendingMachineCommodityListDto;
import com.smartrm.smartrmmonolith.trade.domain.PaymentQrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/trade")
public class TradeController {
    
    @Autowired
    private AppTradeService tradeService;
    
    @RequestMapping(value = "/slot/listCommodity/{machineId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<VendingMachineCommodityListDto> listCommodity(
            @PathVariable Long machineId) {
        return CommonResponse.success(tradeService.queryCommodityList(machineId));
    }
    
    @RequestMapping(value = "/slot/select", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<PaymentQrCode> selectCommodity(@RequestBody SelectCommodityCmdDto cmd) {
        return CommonResponse.success(tradeService.selectCommodity(cmd));
    }
    
    @RequestMapping(value = "/cabinet/open/{machineId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse openCabinet(@PathVariable Long machineId) {
        tradeService.openCabinetVendingMachine(machineId);
        return CommonResponse.success();
    }
    
    @RequestMapping(value = "/cabinet/locked", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse cabinetLocked(@RequestBody CabinetLockedNotificationDto notification) {
        CabinetVendingMachineLockedEvent event = new CabinetVendingMachineLockedEvent();
        event.setMachineId(notification.getMachineId());
        event.setInventoryWhenLock(notification.getInventoryWhenLock());
        event.setInventorySnapshotWhenOpen(notification.getInventorySnapshotWhenOpen());
        tradeService.onCabinetLocked(event);
        return CommonResponse.success();
    }
    
}
