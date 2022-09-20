package com.smartrm.smartrmmonolith.commodity.adapter.convertor.plaintext;

import com.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
public class MapPropertyTextParser implements CommodityPropertyParser<Map, String> {
    
    /**
     * 属性分隔符
     */
    private static final String ATTRIBUTE_SEPARATOR = ";";
    /**
     * key-value 分隔符
     */
    private static final String KEY_VALUE_SEPARATOR = ":";
    
    @Override
    public Map parse(String content) {
        Map<String, String> map = new HashMap<>();
        
        if (StringUtils.isNotBlank(content)) {
            String[] values = content.split(ATTRIBUTE_SEPARATOR);
            for (int i = 0; i < values.length; i++) {
                String[] keyValue = values[i].split(KEY_VALUE_SEPARATOR);
                String key = keyValue[0].trim();
                if (keyValue.length >= 2 && StringUtils.isNotBlank(key)) {
                    map.put(key, keyValue[1].trim());
                }
            }
        }
        
        return map;
        
    }
}
