package com.smartrm.smartrmmonolith.payment.domain;

/**
 * @author: yoda
 * @description:
 */
public enum PaymentState {
    Started(0), //开始
    Success(1), //支付成功
    Fail(2), //支付失败
    WaitingForDeduction(3), //等待扣款
    WaitingForRefund(4),  //等待退款
    RefundSuccess(5),//退款成功
    RefundFailed(6),
    Canceled(5);  //已取消
    
    private final int code;
    
    private PaymentState(int code) {
        this.code = code;
    }
    
    public static PaymentState of(int code) {
        for (PaymentState state : values()) {
            if (state.code == code) {
                return state;
            }
        }
        return null;
    }
    
    public int code() {
        return this.code;
    }
}
