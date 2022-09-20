package com.smartrm.smartrmmonolith.commodity.adapter.convertor.json;

import com.smartrm.smartrmmonolith.commodity.domain.model.Property;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
public class CurrencyPropertyJsonDumper extends PropertyJsonDumper<BigDecimal> {
    
    @Override
    public void dump(Property<BigDecimal> from) {
        if (from.getMaxRepeat() > 1) {
            root.withArray(from.getName()).add(from.getValue());
        } else {
            root.put(from.getName(), from.getValue());
        }
    }
    
}
