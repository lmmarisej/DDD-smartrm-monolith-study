package com.smartrm.smartrmmonolith.infracore.api;

import static com.smartrm.smartrmmonolith.infracore.api.CommonError.NoError;

public class CommonResponse<T> {
    
    private int code;
    private String msg;
    private T data;
    
    private CommonResponse() {
        this.code = NoError.getCode();
        this.msg = NoError.getMsg();
        this.data = null;
    }
    
    private CommonResponse(T data) {
        this.code = NoError.getCode();
        this.msg = NoError.getMsg();
        this.data = data;
    }
    
    private CommonResponse(ErrorCode errCode) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.data = null;
    }
    
    private CommonResponse(ErrorCode errCode, T data) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.data = data;
    }
    
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(NoError, data);
    }
    
    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>();
    }
    
    public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
        return new CommonResponse<>(errorCode);
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public CommonResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
}
