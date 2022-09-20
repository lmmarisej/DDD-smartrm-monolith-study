package com.smartrm.smartrmmonolith.commodity.domain.model;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yoda
 * @description: 商品schema
 */
@Component
public class CommoditySchemaImpl implements CommoditySchema {
    
    private Map<String, PropertySchema<?>> properties = new HashMap<>();
    
    @PostConstruct
    public void init() {
        properties.put("commodityName", PropertySchema.ofString("commodityName", 1));
        properties.put("commodityId", PropertySchema.ofString("commodityId", 1));
        properties.put("imageUrl", PropertySchema.ofImageUrl("imageUrl", 1));
        properties.put("description", PropertySchema.ofString("description", 1));
        properties.put("firstLevelCategory", PropertySchema.ofString("firstLevelCategory", 1));
        properties.put("secondLevelCategory", PropertySchema.ofString("secondLevelCategory", 1));
        properties.put("thirdLevelCategory", PropertySchema.ofString("thirdLevelCategory", 1));
        properties.put("shortName", PropertySchema.ofString("shortName", 1));
        properties.put("label", PropertySchema.ofString("label", 5));
        properties.put("price", PropertySchema.ofCurrency("price", 1));
        properties.put("barCode", PropertySchema.ofString("barCode", 1));
    }
    
    @Override
    public PropertySchema getPropertySchema(String fieldName) {
        return properties.get(fieldName);
    }
    
    @Override
    public List<PropertySchema> getAllPropertySchemas() {
        return Lists.newArrayList(properties.values());
    }
    
}
