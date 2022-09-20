package com.smartrm.smartrmmonolith.commodity.adapter.convertor.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;
import com.smartrm.smartrmmonolith.commodity.domain.model.ImageUrl;
import com.smartrm.smartrmmonolith.commodity.infrastructure.CommodityError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;

/**
 * @author: yoda
 * @description:
 */
public class ImageUrlPropertyJsonParser implements
        CommodityPropertyParser<ImageUrl, JsonNode> {
    
    protected final ObjectMapper mapper;
    
    public ImageUrlPropertyJsonParser() {
        this.mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    @Override
    public ImageUrl parse(JsonNode fieldNode) {
        if (fieldNode == null || fieldNode.isNull()) {
            return null;
        }
        
        if (!fieldNode.isObject()) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
        
        if (!fieldNode.has("url")) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
        
        try {
            return mapper.treeToValue(fieldNode, ImageUrl.class);
        } catch (JsonProcessingException e) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
    }
}
