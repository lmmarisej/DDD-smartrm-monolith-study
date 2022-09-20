package com.smartrm.smartrmmonolith.commodity.adapter.convertor.json;

import com.smartrm.smartrmmonolith.commodity.domain.model.Property;

import java.time.LocalDate;

/**
 * @author: yoda
 * @description:
 */
public class DatePropertyJsonDumper extends PropertyJsonDumper<LocalDate> {
    
    @Override
    public void dump(Property<LocalDate> from) {
        if (from.getMaxRepeat() > 1) {
            root.withArray(from.getName()).add(from.getValue().toString());
        } else {
            root.put(from.getName(), from.getValue().toString());
        }
    }
}
