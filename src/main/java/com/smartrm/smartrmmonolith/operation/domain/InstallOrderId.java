package com.smartrm.smartrmmonolith.operation.domain;

/**
 * @author: yoda
 * @description: 售卖机安装订单id
 */
public class InstallOrderId {
    
    private final String id;    //内码
    private final String number;  //编号
    
    public InstallOrderId(String id, String number) {
        this.id = id;
        this.number = number;
    }
    
    public String id() {
        return id;
    }
    
    public String number() {
        return number;
    }
}
