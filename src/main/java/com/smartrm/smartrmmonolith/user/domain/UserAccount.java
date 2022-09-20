package com.smartrm.smartrmmonolith.user.domain;

import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;

/**
 * @author: yoda
 * @description:
 */
public class UserAccount {
    
    //账号id
    private long accountId;
    /*
     * 微信代扣（免密）支付协议id， 由用户签署之后微信支付平台
     * 后台生成，平台后台通知到smartrm后台，请求扣款时使用
     */
    private String contractId;
    //微信open_id
    private String wxOpenId;
    //微信union_id
    private String wxUnionId;
    
    private UserAccount() {
    
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public Long getAccountId() {
        return accountId;
    }
    
    public void setAccountId(long accountId) {
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
    
    public String getWxUnionId() {
        return wxUnionId;
    }
    
    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }
    
    public static class Builder {
        
        private long accountId;
        private String contractId;
        private String wxOpenId;
        private String wxUnionId;
        
        public Builder accountId(long accountId) {
            this.accountId = accountId;
            return this;
        }
        
        public Builder contractId(String contractId) {
            this.contractId = contractId;
            return this;
        }
        
        public Builder wxOpenId(String openId) {
            this.wxOpenId = openId;
            return this;
        }
        
        public Builder wxUnionId(String unionId) {
            this.wxUnionId = unionId;
            return this;
        }
        
        public UserAccount build() {
            if (this.wxOpenId == null) {
                throw new DomainException(CommonError.UnExpected);
            }
            UserAccount ret = new UserAccount();
            ret.wxOpenId = this.wxOpenId;
            if (accountId != 0) {
                ret.accountId = accountId;
            }
            if (wxUnionId != null) {
                ret.wxUnionId = wxUnionId;
            }
            if (contractId != null) {
                ret.contractId = contractId;
            }
            return ret;
        }
        
        
    }
}
