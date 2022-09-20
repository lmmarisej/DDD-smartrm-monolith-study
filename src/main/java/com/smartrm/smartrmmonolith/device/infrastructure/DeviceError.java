package com.smartrm.smartrmmonolith.device.infrastructure;

import com.smartrm.smartrmmonolith.infracore.api.ErrorCode;

/**
 * @author: yoda
 * @description:
 */
public enum DeviceError implements ErrorCode {
    CabinetStateNotRight(30001, "cabinet vending machine state not right"),
    MachineTypeNotCorrect(30002, "machine type not right"),
    MachineNotExist(30003, "machine not exist"),
    InventoryNotCorrect(30004, "inventory not correct"),
    NoSlotFoundForCommodity(30005, "no slot found for commodity"),
    IoTRequestFail(30006, "iot request failed"),
    UnsupportedDeviceModel(30007, "device model not support"),
    DeviceAccessFailure(30008, "device access failure"),
    FailToPopCommodity(30009, "fail to pop commodity");
    
    private final int code;
    private final String msg;
    
    private DeviceError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public int getCode() {
        return code;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
}
