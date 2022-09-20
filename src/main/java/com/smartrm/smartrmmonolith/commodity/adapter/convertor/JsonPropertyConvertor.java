package com.smartrm.smartrmmonolith.commodity.adapter.convertor;

import com.google.common.collect.ImmutableMap;
import com.smartrm.smartrmmonolith.commodity.adapter.convertor.json.*;
import com.smartrm.smartrmmonolith.commodity.domain.model.ValueType;
import com.smartrm.smartrmmonolith.commodity.domain.model.ValueTypeCode;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.smartrm.smartrmmonolith.commodity.domain.model.ValueTypeCode.*;

/**
 * @author: yoda
 * @description: 适配器，从商品模型到Json对象
 */
@Component
public class JsonPropertyConvertor implements PropertyConvertor {
    
    private static Map<ValueTypeCode, Pair<CommodityPropertyParser, CommodityPropertyDumper>> adapterMap = ImmutableMap
            .<ValueTypeCode, Pair<CommodityPropertyParser, CommodityPropertyDumper>>builder()
            .put(STRING, Pair.of(new StringPropertyJsonParser(), new StringPropertyJsonDumper()))
            .put(INTEGER, Pair.of(new IntegerPropertyJsonParser(), new IntegerPropertyJsonDumper()))
            .put(FLOAT, Pair.of(new FloatPropertyJsonParser(), new FloatPropertyJsonDumper()))
            .put(CURRENCY, Pair.of(new CurrencyPropertyJsonParser(), new CurrencyPropertyJsonDumper()))
            .put(DATE, Pair.of(new DatePropertyJsonParser(), new DatePropertyJsonDumper()))
            .put(DATETIME, Pair.of(new DateTimePropertyJsonParser(), new DateTimePropertyJsonDumper()))
            .put(IMAGE_URL, Pair.of(new ImageUrlPropertyJsonParser(), new ImageUrlPropertyJsonDumper()))
            .put(MAP, Pair.of(new MapPropertyJsonParser(), new MapPropertyJsonDumper()))
            .build();
    
    @Override
    public CommodityPropertyParser parser(ValueType type) {
        return adapterMap.get(type.getType()).getLeft();
    }
    
    @Override
    public CommodityPropertyDumper dumper(ValueType type) {
        return adapterMap.get(type.getType()).getRight();
    }
    
}
