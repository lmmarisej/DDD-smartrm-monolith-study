package com.smartrm.smartrmmonolith.trade.domain.remote;

import java.util.Objects;

/**
 * @author: yoda
 * @description:
 */
public class UserInfo {
    
    //账号id
    private final Long accountId;
    //协议id
    private final String contractId;
    //微信open_id
    private final String wxOpenId;
    //微信union_id
    private final String wxUnionId;
    
    public UserInfo(Long accountId, String contractId, String wxOpenId, String wxUnionId) {
        this.accountId = accountId;
        this.contractId = contractId;
        this.wxOpenId = wxOpenId;
        this.wxUnionId = wxUnionId;
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public Long getAccountId() {
        return accountId;
    }
    
    public String getContractId() {
        return contractId;
    }
    
    public String getWxOpenId() {
        return wxOpenId;
    }
    
    public String getWxUnionId() {
        return wxUnionId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInfo that = (UserInfo) o;
        return accountId.equals(that.accountId) && contractId.equals(that.contractId) && wxOpenId
                .equals(that.wxOpenId) && wxUnionId.equals(that.wxUnionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accountId, contractId, wxOpenId, wxUnionId);
    }
    
    public static class Builder {
        
        //账号id
        private Long accountId;
        //协议id
        private String contractId;
        //微信open_id
        private String wxOpenId;
        //微信union_id
        private String wxUnionId;
        
        public Builder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }
        
        public Builder contractId(String contractId) {
            this.contractId = contractId;
            return this;
        }
        
        public Builder wxOpenId(String wxOpenId) {
            this.wxOpenId = wxOpenId;
            return this;
        }
        
        public Builder wxUnionId(String wxUnionId) {
            this.wxUnionId = wxUnionId;
            return this;
        }
        
        public UserInfo build() {
            return new UserInfo(this.accountId, this.contractId, this.wxOpenId, this.wxUnionId);
        }
    }
}
