package com.smartrm.smartrmmonolith.operation.domain;

/**
 * @author: yoda
 * @description: 安装订单状态
 */
public enum InstallOrderState {
    Placed(0), //已下单
    Approving(1),  //审批中
    Approved(2), //已审批
    Dennied(3),  //已拒绝
    Processing(4), //处理中
    Finished(5); //已完成
    
    
    private final int code;
    
    private InstallOrderState(int code) {
        this.code = code;
    }
    
    public static InstallOrderState of(int code) {
        for (InstallOrderState state : values()) {
            if (state.code == code) {
                return state;
            }
        }
        return null;
    }
    
    public int code() {
        return code;
    }
}
