package com.smartrm.smartrmmonolith.operation.adapter.erp.model;

/**
 * @author: yoda
 * @description: 订单项目
 */
public class FPOOrderEntry {
    
    private Integer FEntryID = 0;   //
    private String FProductType;  //委外产品类型
    private ERPNumberId FMaterialId;    //物料编码
    private ERPNumberId FBomId;         //BOM版本
    private String FMaterialDesc;           //物料说明
    private ERPNumberId FUnitId;        //采购单位
    private Integer FQty;                       //采购数量
    private ERPNumberId FPriceUnitId; //计价单位
    private Integer FPriceUnitQty;         //计价数量
    private ERPNumberId FRequireOrgId;  //需求组织
    private ERPNumberId FRequireDeptId; //需求部门
    private ERPNumberId FReceiveOrgId;   //收料组织
    private ERPNumberId FReceiveDeptId; //收料部门
    private ERPNumberId FSupMatId;        //供应商物料编码
    private String FSupMatName;             //供应商物料名称
    private ERPNumberId FStockUnitID;    //库存单位
    private Integer FStockQty;                  //库存单位数量
    private ERPNumberId FLot;                 //批号
    private String FSupplierLot;                //供应商批号
    private ERPNumberId FProcesser;       //加工商
    private String FDeliveryEarlyDate;      //最早交货日期
    private String FDeliveryLastDate;       //最早交货日期
    private String FContractNo;               //合同单号
    private String FLocation;                   //交货地点
    private ERPNumberId FLocationId;    //交货地点
    //......
    
    
    public Integer getFEntryID() {
        return FEntryID;
    }
    
    public void setFEntryID(Integer FEntryID) {
        this.FEntryID = FEntryID;
    }
    
    public String getFProductType() {
        return FProductType;
    }
    
    public void setFProductType(String FProductType) {
        this.FProductType = FProductType;
    }
    
    public ERPNumberId getFMaterialId() {
        return FMaterialId;
    }
    
    public void setFMaterialId(
            ERPNumberId FMaterialId) {
        this.FMaterialId = FMaterialId;
    }
    
    public ERPNumberId getFBomId() {
        return FBomId;
    }
    
    public void setFBomId(ERPNumberId FBomId) {
        this.FBomId = FBomId;
    }
    
    public String getFMaterialDesc() {
        return FMaterialDesc;
    }
    
    public void setFMaterialDesc(String FMaterialDesc) {
        this.FMaterialDesc = FMaterialDesc;
    }
    
    public ERPNumberId getFUnitId() {
        return FUnitId;
    }
    
    public void setFUnitId(ERPNumberId FUnitId) {
        this.FUnitId = FUnitId;
    }
    
    public Integer getFQty() {
        return FQty;
    }
    
    public void setFQty(Integer FQty) {
        this.FQty = FQty;
    }
    
    public ERPNumberId getFPriceUnitId() {
        return FPriceUnitId;
    }
    
    public void setFPriceUnitId(
            ERPNumberId FPriceUnitId) {
        this.FPriceUnitId = FPriceUnitId;
    }
    
    public Integer getFPriceUnitQty() {
        return FPriceUnitQty;
    }
    
    public void setFPriceUnitQty(Integer FPriceUnitQty) {
        this.FPriceUnitQty = FPriceUnitQty;
    }
    
    public ERPNumberId getFRequireOrgId() {
        return FRequireOrgId;
    }
    
    public void setFRequireOrgId(
            ERPNumberId FRequireOrgId) {
        this.FRequireOrgId = FRequireOrgId;
    }
    
    public ERPNumberId getFRequireDeptId() {
        return FRequireDeptId;
    }
    
    public void setFRequireDeptId(
            ERPNumberId FRequireDeptId) {
        this.FRequireDeptId = FRequireDeptId;
    }
    
    public ERPNumberId getFReceiveOrgId() {
        return FReceiveOrgId;
    }
    
    public void setFReceiveOrgId(
            ERPNumberId FReceiveOrgId) {
        this.FReceiveOrgId = FReceiveOrgId;
    }
    
    public ERPNumberId getFReceiveDeptId() {
        return FReceiveDeptId;
    }
    
    public void setFReceiveDeptId(
            ERPNumberId FReceiveDeptId) {
        this.FReceiveDeptId = FReceiveDeptId;
    }
    
    public ERPNumberId getFSupMatId() {
        return FSupMatId;
    }
    
    public void setFSupMatId(ERPNumberId FSupMatId) {
        this.FSupMatId = FSupMatId;
    }
    
    public String getFSupMatName() {
        return FSupMatName;
    }
    
    public void setFSupMatName(String FSupMatName) {
        this.FSupMatName = FSupMatName;
    }
    
    public ERPNumberId getFStockUnitID() {
        return FStockUnitID;
    }
    
    public void setFStockUnitID(
            ERPNumberId FStockUnitID) {
        this.FStockUnitID = FStockUnitID;
    }
    
    public Integer getFStockQty() {
        return FStockQty;
    }
    
    public void setFStockQty(Integer FStockQty) {
        this.FStockQty = FStockQty;
    }
    
    public ERPNumberId getFLot() {
        return FLot;
    }
    
    public void setFLot(ERPNumberId FLot) {
        this.FLot = FLot;
    }
    
    public String getFSupplierLot() {
        return FSupplierLot;
    }
    
    public void setFSupplierLot(String FSupplierLot) {
        this.FSupplierLot = FSupplierLot;
    }
    
    public ERPNumberId getFProcesser() {
        return FProcesser;
    }
    
    public void setFProcesser(ERPNumberId FProcesser) {
        this.FProcesser = FProcesser;
    }
    
    public String getFDeliveryEarlyDate() {
        return FDeliveryEarlyDate;
    }
    
    public void setFDeliveryEarlyDate(String FDeliveryEarlyDate) {
        this.FDeliveryEarlyDate = FDeliveryEarlyDate;
    }
    
    public String getFDeliveryLastDate() {
        return FDeliveryLastDate;
    }
    
    public void setFDeliveryLastDate(String FDeliveryLastDate) {
        this.FDeliveryLastDate = FDeliveryLastDate;
    }
    
    public String getFContractNo() {
        return FContractNo;
    }
    
    public void setFContractNo(String FContractNo) {
        this.FContractNo = FContractNo;
    }
    
    public String getFLocation() {
        return FLocation;
    }
    
    public void setFLocation(String FLocation) {
        this.FLocation = FLocation;
    }
    
    public ERPNumberId getFLocationId() {
        return FLocationId;
    }
    
    public void setFLocationId(
            ERPNumberId FLocationId) {
        this.FLocationId = FLocationId;
    }
}
