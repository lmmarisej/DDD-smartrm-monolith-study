package com.smartrm.smartrmmonolith.user.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxContractSigningNotification {
    
    private String returnCode;
    private String returnMsg;
    private String resultCode;
    private String mchId;
    private String contractCode;
    private String planId;
    private String openid;
    private String sign;
    private String changeType;
    private String operateTime;
    private String contractId;
    private String requestSerial;
    
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
    
    public String getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    public String getMchId() {
        return mchId;
    }
    
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    
    public String getContractCode() {
        return contractCode;
    }
    
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    
    public String getPlanId() {
        return planId;
    }
    
    public void setPlanId(String planId) {
        this.planId = planId;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getChangeType() {
        return changeType;
    }
    
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    
    public String getOperateTime() {
        return operateTime;
    }
    
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
    
    public String getContractId() {
        return contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    public String getRequestSerial() {
        return requestSerial;
    }
    
    public void setRequestSerial(String requestSerial) {
        this.requestSerial = requestSerial;
    }
}
