package com.smartrm.smartrmmonolith.commodity.adapter.convertor;

import com.smartrm.smartrmmonolith.commodity.domain.model.ValueType;

/**
 * @author: yoda
 * @description:
 */
public interface PropertyConvertor {
    
    CommodityPropertyParser parser(ValueType type);
    
    CommodityPropertyDumper dumper(ValueType type);
    
}
