package com.smartrm.smartrmmonolith.trade.application.dto;

import com.smartrm.smartrmmonolith.trade.domain.SlotVendingMachineState;
import com.smartrm.smartrmmonolith.trade.domain.StockedCommodity;
import com.smartrm.smartrmmonolith.trade.domain.VendingMachineCommodityList;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public class VendingMachineCommodityListDto {
    
    private long machineId;
    private SlotVendingMachineState state;
    private List<StockedCommodity> commodities;
    
    public VendingMachineCommodityListDto(VendingMachineCommodityList list,
                                          SlotVendingMachineState state) {
        this.machineId = list.machineId();
        this.commodities = list.commodities();
        this.state = state;
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    
    public List<StockedCommodity> getCommodities() {
        return commodities;
    }
    
    public void setCommodities(
            List<StockedCommodity> commodities) {
        this.commodities = commodities;
    }
    
    public SlotVendingMachineState getState() {
        return state;
    }
    
    public void setState(SlotVendingMachineState state) {
        this.state = state;
    }
}
