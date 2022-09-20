package com.smartrm.smartrmmonolith.payment.domain;

/**
 * @author: yoda
 * @description:
 */
public enum PaymentType {
    //扫码支付
    QrCodeScan(0),
    //自动扣费
    AutoDedunction(1);
    
    private final int code;
    
    private PaymentType(int code) {
        this.code = code;
    }
    
    public static PaymentType of(int code) {
        for (PaymentType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
    
    public int code() {
        return this.code;
    }
}
