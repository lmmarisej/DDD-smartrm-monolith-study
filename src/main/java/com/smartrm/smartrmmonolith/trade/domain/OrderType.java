package com.smartrm.smartrmmonolith.trade.domain;

/**
 * @author: yoda
 * @description:
 */
public enum OrderType {
    SlotQrScanePaid(0),//货道售卖机扫码支付订单
    CabinetAutoDeduction(1);//货柜机免密支付订单
    
    private final int code;
    
    private OrderType(int code) {
        this.code = code;
    }
    
    public static OrderType of(int code) {
        for (OrderType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
    
    public int code() {
        return code;
    }
    
}
