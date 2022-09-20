package com.smartrm.smartrmmonolith.operation.domain.remote.erp;

import com.smartrm.smartrmmonolith.operation.domain.VendingMachineInstallOrder;

/**
 * @author: yoda
 * @description:
 */
public interface DevicePurchaseService {
    
    void placeInstallOrder(VendingMachineInstallOrder order);
    
}
