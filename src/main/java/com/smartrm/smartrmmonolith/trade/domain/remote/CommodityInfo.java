package com.smartrm.smartrmmonolith.trade.domain.remote;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
public class CommodityInfo {
    
    private final String commodityId;
    private final String commodityName;
    private final String imageUrl;
    private final BigDecimal price;
    
    public CommodityInfo(String commodityId, String commodityName, String imageUrl,
                         BigDecimal price) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.imageUrl = imageUrl;
        this.price = price;
    }
    
    public String getCommodityId() {
        return commodityId;
    }
    
    public String getCommodityName() {
        return commodityName;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
}
