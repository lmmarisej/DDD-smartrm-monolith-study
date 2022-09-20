package com.smartrm.smartrmmonolith.commodity.domain.model;

public class ValueTypeImageUrl implements ValueType<ImageUrl> {
    
    @Override
    public ValueTypeCode getType() {
        return ValueTypeCode.IMAGE_URL;
    }
    
    @Override
    public Class<ImageUrl> getValueClass() {
        return ImageUrl.class;
    }
    
}
