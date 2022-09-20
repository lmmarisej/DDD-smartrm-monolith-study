package com.smartrm.smartrmmonolith.trade.domain;

/**
 * @author: yoda
 * @description:
 */
public class PaymentQrCode {
    
    private long paymentId;
    private String codeUrl;
    
    public PaymentQrCode(long paymentId, String codeUrl) {
        this.paymentId = paymentId;
        this.codeUrl = codeUrl;
    }
    
    public String getCodeUrl() {
        return codeUrl;
    }
    
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
    
    public long getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }
}
