package com.smartrm.smartrmmonolith.payment.domain;

/**
 * @author: yoda
 * @description:
 */
public enum PlatformType {
    Wechat(0);
    
    private final int code;
    
    private PlatformType(int code) {
        this.code = code;
    }
    
    public static PlatformType of(int code) {
        for (PlatformType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
    
    public int code() {
        return this.code;
    }
}
