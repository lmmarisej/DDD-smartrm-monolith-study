package com.smartrm.smartrmmonolith.trade.infrastructure;

import com.smartrm.smartrmmonolith.infracore.api.ErrorCode;

/**
 * @author: yoda
 * @description:
 */
public enum TradeError implements ErrorCode {
    VendingMachineStateNotRight(50001, "vending machine state not right"),
    OrderStateErrorWhenCancel(50002, "order state not right when cancel"),
    VendingMachineNotFound(50003, "vending machine not found."),
    CommodityNotExist(50004, "commodity not exist"),
    InventoryCheckFail(50005, "not enough commodity in this machine"),
    OrderNotExist(50006, "order not exist"),
    OrderAmountZero(50007, "order amount can not be zero");
    
    
    private final int code;
    private final String msg;
    
    private TradeError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public int getCode() {
        return code;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
}
