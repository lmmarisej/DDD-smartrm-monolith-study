package com.smartrm.smartrmmonolith.commodity.adapter.convertor;

import com.smartrm.smartrmmonolith.commodity.domain.model.Property;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityPropertyDumper<T> {
    
    void dump(Property<T> from);
    
}
