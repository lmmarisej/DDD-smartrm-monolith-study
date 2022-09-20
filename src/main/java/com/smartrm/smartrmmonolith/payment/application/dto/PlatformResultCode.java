package com.smartrm.smartrmmonolith.payment.application.dto;

/**
 * @author: yoda
 * @description:
 */
public enum PlatformResultCode {
    Success(0),
    Fail(1);
    
    private final int code;
    
    private PlatformResultCode(int code) {
        this.code = code;
    }
    
    public static PlatformResultCode of(int code) {
        for (PlatformResultCode resultCode : values()) {
            if (resultCode.code == code) {
                return resultCode;
            }
        }
        return null;
    }
    
    public int code() {
        return this.code;
    }
}
