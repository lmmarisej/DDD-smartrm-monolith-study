package com.smartrm.smartrmmonolith.user.application.dto;

/**
 * @author: yoda
 * @description:
 */
public enum SignInResultCode {
    Success(0),
    NeedSignContract(1),
    Fail(2);
    
    private final int code;
    
    private SignInResultCode(int code) {
        this.code = code;
    }
    
    public int code() {
        return this.code;
    }
    
}
