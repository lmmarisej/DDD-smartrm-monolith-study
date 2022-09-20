package com.smartrm.smartrmmonolith.payment.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxAmountDto {
    
    private Integer total;
    private Integer payerTotal;
    private String currency;
    private String payerCurrency;
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public Integer getPayerTotal() {
        return payerTotal;
    }
    
    public void setPayerTotal(Integer payerTotal) {
        this.payerTotal = payerTotal;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getPayerCurrency() {
        return payerCurrency;
    }
    
    public void setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
    }
}
