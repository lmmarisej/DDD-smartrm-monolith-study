package com.smartrm.smartrmmonolith.user.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class SignInResultDto {
    
    private SignInResultCode result;
    private String token;
    
    public SignInResultCode getResult() {
        return result;
    }
    
    public void setResult(SignInResultCode result) {
        this.result = result;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
