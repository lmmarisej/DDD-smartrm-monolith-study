package com.smartrm.smartrmmonolith.user.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxContractSigningResponse {
    
    private String returnCode;
    private String returnMsg;
    
    public String getReturnCode() {
        return returnCode;
    }
    
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    
    public String getReturnMsg() {
        return returnMsg;
    }
    
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
