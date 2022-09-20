package com.smartrm.smartrmmonolith.payment.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxPaymentSuccessDto {
    
    private String appid;
    private String mchid;
    private String outTradeNo;
    private String transactionId;
    private String tradeType;
    private String tradeState;
    private String tradeStateDesc;
    private String bankType;
    private String successTime;
    private WxPayerDto payer;
    private WxAmountDto amount;
    
    public WxPayerDto getPayer() {
        return payer;
    }
    
    public void setPayer(WxPayerDto payer) {
        this.payer = payer;
    }
    
    public WxAmountDto getAmount() {
        return amount;
    }
    
    public void setAmount(WxAmountDto amount) {
        this.amount = amount;
    }
    
    public String getAppid() {
        return appid;
    }
    
    public void setAppid(String appid) {
        this.appid = appid;
    }
    
    public String getMchid() {
        return mchid;
    }
    
    public void setMchid(String mchid) {
        this.mchid = mchid;
    }
    
    public String getOutTradeNo() {
        return outTradeNo;
    }
    
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getTradeType() {
        return tradeType;
    }
    
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    
    public String getTradeState() {
        return tradeState;
    }
    
    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }
    
    public String getTradeStateDesc() {
        return tradeStateDesc;
    }
    
    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
    
    public String getBankType() {
        return bankType;
    }
    
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    
    public String getSuccessTime() {
        return successTime;
    }
    
    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }
}
