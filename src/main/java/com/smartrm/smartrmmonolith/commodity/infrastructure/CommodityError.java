package com.smartrm.smartrmmonolith.commodity.infrastructure;

import com.smartrm.smartrmmonolith.infracore.api.ErrorCode;

/**
 * @author: yoda
 * @description:
 */
public enum CommodityError implements ErrorCode {
    
    CommodityParseError(20001, "commodity parse error"),
    CommodityValidateError(20002, "commodity validate error"),
    CommodityPropertyReachMaxRepeat(20003, "commodity reach max repeat limit"),
    CommodityNoSuchProperty(20004, "commodity no such property");
    
    private final int code;
    private final String msg;
    
    private CommodityError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public int getCode() {
        return 0;
    }
    
    @Override
    public String getMsg() {
        return null;
    }
}
