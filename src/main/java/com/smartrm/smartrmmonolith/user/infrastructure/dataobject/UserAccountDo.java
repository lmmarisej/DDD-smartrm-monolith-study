package com.smartrm.smartrmmonolith.user.infrastructure.dataobject;

/**
 * @author: yoda
 * @description:
 */
public class UserAccountDo {
    
    //账号id
    private Long accountId;
    /*
     * 微信代扣（免密）支付协议id， 由用户签署之后微信支付平台
     * 后台生成，平台后台通知到smartrm后台，请求扣款时使用
     */
    private String contractId;
    //微信open_id
    private String wxOpenId;
    //微信union_id
    private String wxUnionId;
    
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
