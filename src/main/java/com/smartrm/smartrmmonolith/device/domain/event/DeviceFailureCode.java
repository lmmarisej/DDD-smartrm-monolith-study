package com.smartrm.smartrmmonolith.device.domain.event;

/**
 * @author: yoda
 * @description:
 */
public enum DeviceFailureCode {
    DeviceError(1),
    NetworkFailure(2),
    CommodityStucked(3);
    
    private final int code;
    
    private DeviceFailureCode(int code) {
        this.code = code;
    }
}
