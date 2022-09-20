package com.smartrm.smartrmmonolith.commodity.adapter.convertor.plaintext;

import com.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;
import com.smartrm.smartrmmonolith.commodity.domain.model.DateParser;
import com.smartrm.smartrmmonolith.commodity.infrastructure.CommodityError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * @author: yoda
 * @description:
 */
public class DatePropertyTextParser implements
        CommodityPropertyParser<LocalDate, String> {
    
    @Override
    public LocalDate parse(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        
        LocalDate date = DateParser.asLocalDate(value);
        if (date == null) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
        return date;
    }
}
