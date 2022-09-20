package com.smartrm.smartrmmonolith.payment.application.dto;

import com.smartrm.smartrmmonolith.payment.domain.PlatformType;

/**
 * @author: yoda
 * @description:
 */
public class PlatformPaymentResultDto {
    
    private PlatformType platformType;
    private Long orderId;
    private PlatformResultCode resultCode;
    
    public PlatformType getPlatformType() {
        return platformType;
    }
    
    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public PlatformResultCode getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(
            PlatformResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
