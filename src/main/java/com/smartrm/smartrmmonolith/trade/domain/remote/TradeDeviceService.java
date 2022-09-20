package com.smartrm.smartrmmonolith.trade.domain.remote;

import com.smartrm.smartrmmonolith.infracore.exception.DomainException;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public interface TradeDeviceService {
    
    /**
     * 获取售卖机库存信息
     *
     * @param machineId
     * @return
     */
    List<InventoryInfo> getInventory(long machineId);
    
    /**
     * 弹出商品
     */
    void popCommodity(long machineId, String commodityId, long orderId) throws Exception;
    
    /**
     * 打开货柜机
     *
     * @param machineId 售卖机id
     */
    void openCabinetVendingMachine(long machineId) throws DomainException;
    
}
