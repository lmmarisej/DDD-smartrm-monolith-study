package com.smartrm.smartrmmonolith.commodity.adapter.convertor.plaintext;

import com.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;
import com.smartrm.smartrmmonolith.commodity.infrastructure.CommodityError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
public class CurrencyPropertyTextParser implements
        CommodityPropertyParser<BigDecimal, String> {
    
    @Override
    public BigDecimal parse(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        
        try {
            return NumberUtils.createBigDecimal(value);
        } catch (NumberFormatException e) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
    }
}
