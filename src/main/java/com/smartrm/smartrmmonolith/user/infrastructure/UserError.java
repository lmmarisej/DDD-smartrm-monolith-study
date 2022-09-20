package com.smartrm.smartrmmonolith.user.infrastructure;

import com.smartrm.smartrmmonolith.infracore.api.ErrorCode;

/**
 * @author: yoda
 * @description:
 */
public enum UserError implements ErrorCode {
    FailToLoginWx(40001, "fail to login wechat platform"),
    NeedSignContract(40002, "need to sign payment contract"); //需要签署支付协议以便开通免密支付
    
    private final int code;
    private final String msg;
    
    private UserError(int code, String msg) {
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
