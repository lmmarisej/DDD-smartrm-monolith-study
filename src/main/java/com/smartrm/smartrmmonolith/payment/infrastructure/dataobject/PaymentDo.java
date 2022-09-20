package com.smartrm.smartrmmonolith.payment.infrastructure.dataobject;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author: yoda
 * @description:
 */
public class PaymentDo {
    
    private Long paymentId;
    private Integer paymentType;
    private Integer platformType;
    private Long accountId;
    private String contractId;
    private Long machineId;
    private Long orderId;
    private Integer orderType;
    private Integer state;
    private BigDecimal amount;
    private Integer retriedTimes;
    private Timestamp lastRetryTime;
    
    public Long getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    
    public Integer getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
    
    public Integer getPlatformType() {
        return platformType;
    }
    
    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
    
    public Integer getRetriedTimes() {
        return retriedTimes;
    }
    
    public void setRetriedTimes(Integer retriedTimes) {
        this.retriedTimes = retriedTimes;
    }
    
    public Timestamp getLastRetryTime() {
        return lastRetryTime;
    }
    
    public void setLastRetryTime(Timestamp lastRetryTime) {
        this.lastRetryTime = lastRetryTime;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public Integer getOrderType() {
        return orderType;
    }
    
    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
