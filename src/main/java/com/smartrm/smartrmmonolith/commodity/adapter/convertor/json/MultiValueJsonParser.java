package com.smartrm.smartrmmonolith.commodity.adapter.convertor.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public class MultiValueJsonParser<T> implements
        CommodityPropertyParser<List<T>, ArrayNode> {
    
    private CommodityPropertyParser<T, JsonNode> parser;
    
    public MultiValueJsonParser(CommodityPropertyParser<T, JsonNode> parser) {
        this.parser = parser;
    }
    
    @Override
    public List<T> parse(ArrayNode fieldNode) {
        if (fieldNode == null || fieldNode.isNull()) {
            return null;
        }
        
        List<T> list = new ArrayList<>();
        for (JsonNode node : fieldNode) {
            list.add(parser.parse(node));
        }
        return list;
    }
}
