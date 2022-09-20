package com.smartrm.smartrmmonolith.infracore.api;

public enum CommonError implements ErrorCode {
    NoError(0, "success"),
    PersistentDataError(1, "data format error"),
    DataConsistencyError(2, "data consistency error"),
    InvalidProperty(3, "invalid property"),
    SchedulerError(4, "scheduler error"),
    ConcurrencyConflict(5, "concurrency conflict"),
    FailToGetLocalIp(6, "fail to get local ip"),
    NoEventBus(7, "no event bus appointed"),
    RequestParamsInvalid(400, "request parameters invalid"),//参数校验错误
    NeedAuthentication(401, "need authentication"),//需登录认证
    NotAuthorized(403, "not authorized"),//未授权
    UnExpected(10000, "unexpected error");
    
    private final int code;
    private final String msg;
    
    CommonError(final int code, final String msg) {
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
