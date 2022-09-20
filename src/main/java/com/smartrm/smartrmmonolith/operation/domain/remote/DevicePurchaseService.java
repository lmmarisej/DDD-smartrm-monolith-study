package com.smartrm.smartrmmonolith.operation.domain.remote;

import com.smartrm.smartrmmonolith.operation.domain.VendingMachineInstallOrder;

/**
 * @author: yoda
 * @description: 售卖机投放（采购）服务
 */
public interface DevicePurchaseService {
    
    void placeInstallOrder(VendingMachineInstallOrder order);
    
}
