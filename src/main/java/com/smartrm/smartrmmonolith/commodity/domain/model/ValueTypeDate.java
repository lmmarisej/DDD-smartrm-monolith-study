package com.smartrm.smartrmmonolith.commodity.domain.model;

import java.time.LocalDate;

public class ValueTypeDate implements ValueType<LocalDate> {
    
    @Override
    public ValueTypeCode getType() {
        return ValueTypeCode.DATE;
    }
    
    @Override
    public Class<LocalDate> getValueClass() {
        return LocalDate.class;
    }
    
}
