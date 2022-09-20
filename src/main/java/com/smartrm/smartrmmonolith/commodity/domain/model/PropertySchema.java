package com.smartrm.smartrmmonolith.commodity.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class PropertySchema<T> {
    
    //字段名
    private String name;
    
    //字段标签
    private String label = "";
    
    //最大重复次数，默认不重复
    private int maxRepeat = 1;
    
    //数据类型
    private ValueType<T> valueType;
    
    //样例
    private String example = "";
    
    //说明
    private String desc = "";
    
    public PropertySchema(String name, ValueType<T> type) {
        this.name = name;
        this.valueType = type;
    }
    
    public static PropertySchema<BigDecimal> ofCurrency(String name, int maxRepeat) {
        PropertySchema<BigDecimal> ret = new PropertySchema<>(name, new ValueTypeCurrency());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<LocalDate> ofDate(String name, int maxRepeat) {
        PropertySchema<LocalDate> ret = new PropertySchema<>(name, new ValueTypeDate());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<LocalDateTime> ofDateTime(String name, int maxRepeat) {
        PropertySchema<LocalDateTime> ret = new PropertySchema<>(name, new ValueTypeDateTime());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<Double> ofFloat(String name, int maxRepeat) {
        PropertySchema<Double> ret = new PropertySchema<>(name, new ValueTypeFloat());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<ImageUrl> ofImageUrl(String name, int maxRepeat) {
        PropertySchema<ImageUrl> ret = new PropertySchema<>(name, new ValueTypeImageUrl());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<Long> ofInteger(String name, int maxRepeat) {
        PropertySchema<Long> ret = new PropertySchema<>(name, new ValueTypeInteger());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<Map> ofMap(String name, int maxRepeat) {
        PropertySchema<Map> ret = new PropertySchema<>(name, new ValueTypeMap());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public static PropertySchema<String> ofString(String name, int maxRepeat) {
        PropertySchema<String> ret = new PropertySchema<>(name, new ValueTypeString());
        ret.setMaxRepeat(maxRepeat);
        return ret;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public int getMaxRepeat() {
        return maxRepeat;
    }
    
    public void setMaxRepeat(int maxRepeat) {
        this.maxRepeat = maxRepeat;
    }
    
    public ValueType<T> getValueType() {
        return valueType;
    }
    
    public void setValueType(ValueType<T> valueType) {
        this.valueType = valueType;
    }
    
    public String getExample() {
        return example;
    }
    
    public void setExample(String example) {
        this.example = example;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
