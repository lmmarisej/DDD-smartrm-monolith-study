package com.smartrm.smartrmmonolith.operation.adapter.erp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yoda
 * @description: ERP采购订单
 */
public class ERPPurchaseOrder {
    
    private String FBusinessType;
    private String FBillNo;
    private String FDate; //采购日期
    private String FDocumentStatus;
    private ERPNumberId FBillTypeID;  //单据类型
    private ERPNumberId FSupplierId;  //供应商
    private ERPNumberId FPurchaseOrgId; //采购组织
    private ERPNumberId FPurchaseDeptId;  //采购部门
    private ERPNumberId FPurchaserGroupId;  //采购组
    private ERPNumberId FPurchaserId;           //采购员
    private List<FPOOrderEntry> FPOOrderEntry = new ArrayList<>();  //订单项目
    
    public String getFBusinessType() {
        return FBusinessType;
    }
    
    public void setFBusinessType(String FBusinessType) {
        this.FBusinessType = FBusinessType;
    }
    
    public String getFBillNo() {
        return FBillNo;
    }
    
    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }
    
    public String getFDate() {
        return FDate;
    }
    
    public void setFDate(String FDate) {
        this.FDate = FDate;
    }
    
    public String getFDocumentStatus() {
        return FDocumentStatus;
    }
    
    public void setFDocumentStatus(String FDocumentStatus) {
        this.FDocumentStatus = FDocumentStatus;
    }
    
    public ERPNumberId getFBillTypeID() {
        return FBillTypeID;
    }
    
    public void setFBillTypeID(
            ERPNumberId FBillTypeID) {
        this.FBillTypeID = FBillTypeID;
    }
    
    public ERPNumberId getFSupplierId() {
        return FSupplierId;
    }
    
    public void setFSupplierId(
            ERPNumberId FSupplierId) {
        this.FSupplierId = FSupplierId;
    }
    
    public ERPNumberId getFPurchaseOrgId() {
        return FPurchaseOrgId;
    }
    
    public void setFPurchaseOrgId(
            ERPNumberId FPurchaseOrgId) {
        this.FPurchaseOrgId = FPurchaseOrgId;
    }
    
    public ERPNumberId getFPurchaseDeptId() {
        return FPurchaseDeptId;
    }
    
    public void setFPurchaseDeptId(
            ERPNumberId FPurchaseDeptId) {
        this.FPurchaseDeptId = FPurchaseDeptId;
    }
    
    public ERPNumberId getFPurchaserGroupId() {
        return FPurchaserGroupId;
    }
    
    public void setFPurchaserGroupId(
            ERPNumberId FPurchaserGroupId) {
        this.FPurchaserGroupId = FPurchaserGroupId;
    }
    
    public ERPNumberId getFPurchaserId() {
        return FPurchaserId;
    }
    
    public void setFPurchaserId(
            ERPNumberId FPurchaserId) {
        this.FPurchaserId = FPurchaserId;
    }
    
    public void addEntry(FPOOrderEntry entry) {
        FPOOrderEntry.add(entry);
    }
    
    public List<FPOOrderEntry> getFPOOrderEntry() {
        return FPOOrderEntry;
    }
    
    public void setFPOOrderEntry(List<FPOOrderEntry> FPOOrderEntry) {
        this.FPOOrderEntry = FPOOrderEntry;
    }
}
