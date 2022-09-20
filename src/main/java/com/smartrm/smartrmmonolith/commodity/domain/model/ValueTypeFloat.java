package com.smartrm.smartrmmonolith.commodity.domain.model;

public class ValueTypeFloat implements ValueType<Double> {
    
    @Override
    public ValueTypeCode getType() {
        return ValueTypeCode.FLOAT;
    }
    
    @Override
    public Class<Double> getValueClass() {
        return Double.class;
    }
    
}
