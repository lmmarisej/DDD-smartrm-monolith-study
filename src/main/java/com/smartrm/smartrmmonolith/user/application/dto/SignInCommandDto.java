package com.smartrm.smartrmmonolith.user.application.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author: yoda
 * @description:
 */
public class SignInCommandDto {
    
    @NotBlank
    String wxJsCode;
    
    public String getWxJsCode() {
        return wxJsCode;
    }
    
    public void setWxJsCode(String wxJsCode) {
        this.wxJsCode = wxJsCode;
    }
}
