package com.smartrm.smartrmmonolith.user.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxCode2SessionResultDto {
    
    private String openId;
    private String unionId;
    private String sessionKey;
    private Integer errcode;
    private String errmsg;
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getSessionKey() {
        return sessionKey;
    }
    
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
    
    public Integer getErrcode() {
        return errcode;
    }
    
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    
    public String getErrmsg() {
        return errmsg;
    }
    
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    
    public String getUnionId() {
        return unionId;
    }
    
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
