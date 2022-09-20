package com.smartrm.smartrmmonolith.operation.adapter.erp.model;

/**
 * @author: yoda
 * @description:
 */
public class ERPNumberId {
    
    private String FNumber = "";
    
    public ERPNumberId(String number) {
        FNumber = number;
    }
    
    public ERPNumberId() {
    
    }
    
    public String getFNumber() {
        return FNumber;
    }
    
    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }
}
