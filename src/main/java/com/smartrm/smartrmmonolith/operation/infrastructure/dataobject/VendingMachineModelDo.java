package com.smartrm.smartrmmonolith.operation.infrastructure.dataobject;

/**
 * @author: yoda
 * @description: 售卖机型号数据
 */
public class VendingMachineModelDo {
    
    private int modelCode;
    private int type;
    private String supplierId;
    private String purchaseOrgId;
    private String purchaseDeptId;
    private String purchaseGroupId;
    private String purchaserId;
    private String productType;
    private String materialId;
    private String bomId;
    private String materialDesc;
    private String processor;
    private int slotNum;
    private int perSlotCapacity;
    private int layerNum;
    private int perLayerCapacity;
    
    public int getModelCode() {
        return modelCode;
    }
    
    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getSupplierId() {
        return supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    public String getPurchaseOrgId() {
        return purchaseOrgId;
    }
    
    public void setPurchaseOrgId(String purchaseOrgId) {
        this.purchaseOrgId = purchaseOrgId;
    }
    
    public String getPurchaseDeptId() {
        return purchaseDeptId;
    }
    
    public void setPurchaseDeptId(String purchaseDeptId) {
        this.purchaseDeptId = purchaseDeptId;
    }
    
    public String getPurchaseGroupId() {
        return purchaseGroupId;
    }
    
    public void setPurchaseGroupId(String purchaseGroupId) {
        this.purchaseGroupId = purchaseGroupId;
    }
    
    public String getPurchaserId() {
        return purchaserId;
    }
    
    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public String getMaterialId() {
        return materialId;
    }
    
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
    
    public String getBomId() {
        return bomId;
    }
    
    public void setBomId(String bomId) {
        this.bomId = bomId;
    }
    
    public String getMaterialDesc() {
        return materialDesc;
    }
    
    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }
    
    public String getProcessor() {
        return processor;
    }
    
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    
    public int getSlotNum() {
        return slotNum;
    }
    
    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }
    
    public int getPerSlotCapacity() {
        return perSlotCapacity;
    }
    
    public void setPerSlotCapacity(int perSlotCapacity) {
        this.perSlotCapacity = perSlotCapacity;
    }
    
    public int getLayerNum() {
        return layerNum;
    }
    
    public void setLayerNum(int layerNum) {
        this.layerNum = layerNum;
    }
    
    public int getPerLayerCapacity() {
        return perLayerCapacity;
    }
    
    public void setPerLayerCapacity(int perLayerCapacity) {
        this.perLayerCapacity = perLayerCapacity;
    }
}
