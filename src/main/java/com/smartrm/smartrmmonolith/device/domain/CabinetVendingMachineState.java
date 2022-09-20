package com.smartrm.smartrmmonolith.device.domain;

/**
 * @author: yoda
 * @description:
 */
public enum CabinetVendingMachineState {
    Locked(0),
    Open(1);
    
    private final int code;
    
    private CabinetVendingMachineState(int code) {
        this.code = code;
    }
    
    public static CabinetVendingMachineState of(int code) {
        for (CabinetVendingMachineState state : values()) {
            if (state.code == code) {
                return state;
            }
        }
        return null;
    }
    
    public int code() {
        return code;
    }
}
