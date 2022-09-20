package com.smartrm.smartrmmonolith.trade.domain;

import com.smartrm.smartrmmonolith.infracore.common.MathUtil;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description: 库存商品
 */
public final class StockedCommodity {
    
    private final String commodityId;
    private final String name;
    private final String imageUrl;
    private final BigDecimal price;
    private final int count;
    
    public StockedCommodity(String commodityId, String name, String imageUrl, BigDecimal price,
                            int count) {
        this.commodityId = commodityId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.count = count;
    }
    
    public StockedCommodity(StockedCommodity copy) {
        this.commodityId = copy.commodityId;
        this.price = copy.price;
        this.count = copy.count;
        this.imageUrl = copy.imageUrl;
        this.name = copy.name;
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public String getCommodityId() {
        return commodityId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public int getCount() {
        return count;
    }
    
    public StockedCommodity withCount(int count) {
        StockedCommodity ret = new StockedCommodity(this.commodityId, this.name, this.imageUrl,
                this.price, count);
        return ret;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        StockedCommodity commodity = (StockedCommodity) other;
        return this.commodityId.equals(commodity.commodityId)
                && this.name.equals(commodity.name)
                && this.imageUrl.equals(commodity.imageUrl)
                && this.count == commodity.count;
    }
    
    @Override
    public int hashCode() {
        return MathUtil.HASH_MAGIC_NUMBER
                + this.commodityId.hashCode()
                + this.name.hashCode()
                + this.imageUrl.hashCode() + this.count;
    }
    
    public static class Builder {
        
        private String commodityId;
        private String name;
        private String imageUrl;
        private BigDecimal price;
        private int count;
        
        public Builder commodityId(String commodityId) {
            this.commodityId = commodityId;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public Builder count(int count) {
            this.count = count;
            return this;
        }
        
        public StockedCommodity build() {
            return new StockedCommodity(this.commodityId, this.name, this.imageUrl, this.price,
                    this.count);
        }
        
    }
    
}
