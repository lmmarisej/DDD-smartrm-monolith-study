package com.smartrm.smartrmmonolith.commodity.domain.model;

public class ValueTypeString implements ValueType<String> {
    
    @Override
    public ValueTypeCode getType() {
        return ValueTypeCode.STRING;
    }
    
    @Override
    public Class<String> getValueClass() {
        return String.class;
    }
    
}
