package com.smartrm.smartrmmonolith.trade.infrastructure.dataobject;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
public class StockedCommodityDo {
    
    private String commodityId;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private int count;
    
    public String getCommodityId() {
        return commodityId;
    }
    
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
