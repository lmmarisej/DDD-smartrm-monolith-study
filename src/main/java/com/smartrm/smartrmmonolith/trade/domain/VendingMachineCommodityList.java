package com.smartrm.smartrmmonolith.trade.domain;

import com.smartrm.smartrmmonolith.infracore.common.MathUtil;

import java.util.List;

/**
 * @author: yoda
 * @description: 交易上下文-售卖机商品列表
 */
public final class VendingMachineCommodityList {
    
    private final long machineId;
    private final List<StockedCommodity> commodities;
    
    public VendingMachineCommodityList(long machineId, List<StockedCommodity> commodities) {
        this.machineId = machineId;
        this.commodities = commodities;
    }
    
    public long machineId() {
        return machineId;
    }
    
    public List<StockedCommodity> commodities() {
        return commodities;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        VendingMachineCommodityList list = (VendingMachineCommodityList) other;
        return this.machineId == list.machineId && list.commodities.equals(this.commodities);
    }
    
    @Override
    public int hashCode() {
        int ret = MathUtil.HASH_MAGIC_NUMBER + (int) this.machineId + this.commodities.hashCode();
        return ret;
    }
    
}
