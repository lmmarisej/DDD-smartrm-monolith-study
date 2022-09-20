package com.smartrm.smartrmmonolith.commodity.application.dto;

import com.smartrm.smartrmmonolith.commodity.domain.model.Commodity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: yoda
 * @description: 商品数据传输对象
 */
public class CommodityInfoDto {
    
    public static final String PROPERTY_NAME_ID = "commodityId";
    
    public static final String PROPERTY_NAME_NAME = "commodityName";
    
    public static final String PROPERTY_NAME_IMAGE_URL = "imageUrl";
    
    public static final String PROPERTY_NAME_DESCRIPTION = "description";
    
    public static final String PROPERTY_NAME_FIRST_LEVEL_CATEGORY = "firstLevelCategory";
    
    public static final String PROPERTY_NAME_SECOND_LEVEL_CATEGORY = "secondLevelCategory";
    
    public static final String PROPERTY_NAME_THIRD_LEVEL_CATEGORY = "thirdLevelCategory";
    
    public static final String PROPERTY_NAME_LABEL = "label";
    
    public static final String PROPERTY_NAME_PRICE = "price";
    
    public static final String PROPERTY_NAME_BARCODE = "barCode";
    
    private String commodityId;
    private String commodityName;
    private String imageUrl;
    private String description;
    private String firstCategoryName;
    private String secondCategoryName;
    private String thirdCategoryName;
    private String barCode;
    private List<String> labels;
    private BigDecimal price;
    
    public CommodityInfoDto(Commodity commodity) {
        commodityId = commodity.getAsString(PROPERTY_NAME_ID);
        commodityName = commodity.getAsString(PROPERTY_NAME_NAME);
        imageUrl = commodity.getAsImageUrl(PROPERTY_NAME_IMAGE_URL).getUrl();
        description = commodity.getAsString(PROPERTY_NAME_DESCRIPTION);
        firstCategoryName = commodity.getAsString(PROPERTY_NAME_FIRST_LEVEL_CATEGORY);
        secondCategoryName = commodity.getAsString(PROPERTY_NAME_SECOND_LEVEL_CATEGORY);
        thirdCategoryName = commodity.getAsString(PROPERTY_NAME_THIRD_LEVEL_CATEGORY);
        barCode = commodity.getAsString(PROPERTY_NAME_BARCODE);
        labels = commodity.<String>getAsList(PROPERTY_NAME_LABEL);
        price = commodity.getAsBigDecimal(PROPERTY_NAME_PRICE);
        
    }
    
    public String getCommodityId() {
        return commodityId;
    }
    
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    
    public String getCommodityName() {
        return commodityName;
    }
    
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFirstCategoryName() {
        return firstCategoryName;
    }
    
    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }
    
    public String getSecondCategoryName() {
        return secondCategoryName;
    }
    
    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }
    
    public String getThirdCategoryName() {
        return thirdCategoryName;
    }
    
    public void setThirdCategoryName(String thirdCategoryName) {
        this.thirdCategoryName = thirdCategoryName;
    }
    
    public String getBarCode() {
        return barCode;
    }
    
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    
    public List<String> getLabels() {
        return labels;
    }
    
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
