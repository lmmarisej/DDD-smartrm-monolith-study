package com.smartrm.smartrmmonolith.payment.domain;

/**
 * @author: yoda
 * @description:
 */
public class AccountInfo {
    
    private Long accountId;
    private String contractId;
    
    public AccountInfo(Long accountId, String contractId) {
        this.accountId = accountId;
        this.contractId = contractId;
    }
    
    public Long getAccountId() {
        return accountId;
    }
    
    public String getContractId() {
        return contractId;
    }
    
}
