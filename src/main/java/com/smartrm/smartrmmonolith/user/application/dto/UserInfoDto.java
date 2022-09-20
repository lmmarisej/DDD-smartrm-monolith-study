package com.smartrm.smartrmmonolith.user.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class UserInfoDto {
    
    //账号id
    private Long accountId;
    //协议id
    private String contractId;
    //微信open_id
    private String wxOpenId;
    //微信union_id
    private String wxUnionId;
    
    public UserInfoDto() {
    
    }
    
    public Long getAccountId() {
        return accountId;
    }
    
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    public String getContractId() {
        return contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    public String getWxOpenId() {
        return wxOpenId;
    }
    
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
    
    public String getWxUnionId() {
        return wxUnionId;
    }
    
    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }
    
}
