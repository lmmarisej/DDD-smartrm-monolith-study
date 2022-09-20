package com.smartrm.smartrmmonolith.commodity.adapter.convertor;

import com.google.common.collect.ImmutableMap;
import com.smartrm.smartrmmonolith.commodity.adapter.convertor.plaintext.*;
import com.smartrm.smartrmmonolith.commodity.domain.model.ValueType;
import com.smartrm.smartrmmonolith.commodity.domain.model.ValueTypeCode;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.smartrm.smartrmmonolith.commodity.domain.model.ValueTypeCode.*;

/**
 * @author: yoda
 * @description:
 */
@Component
public class PlainTextPropertyConvertor implements PropertyConvertor {
    
    private static Map<ValueTypeCode, Pair<CommodityPropertyParser, CommodityPropertyDumper>> adapterMap = ImmutableMap
            .<ValueTypeCode, Pair<CommodityPropertyParser, CommodityPropertyDumper>>builder()
            .put(STRING, Pair.of(new StringPropertyTextParser(), null))
            .put(INTEGER, Pair.of(new IntegerPropertyTextParser(), null))
            .put(FLOAT, Pair.of(new FloatPropertyTextParser(), null))
            .put(CURRENCY, Pair.of(new CurrencyPropertyTextParser(), null))
            .put(DATE, Pair.of(new DatePropertyTextParser(), null))
            .put(DATETIME, Pair.of(new DateTimePropertyTextParser(), null))
            .put(IMAGE_URL, Pair.of(new ImageUrlPropertyTextParser(null), null))
            .put(MAP, Pair.of(new MapPropertyTextParser(), null))
            .build();
    
    @Override
    public CommodityPropertyParser parser(ValueType type) {
        return adapterMap.get(type.getType()).getLeft();
    }
    
    @Override
    public CommodityPropertyDumper dumper(ValueType type) {
        return null;
    }
}
