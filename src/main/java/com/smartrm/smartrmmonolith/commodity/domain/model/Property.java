package com.smartrm.smartrmmonolith.commodity.domain.model;

//import com.csvreader.CsvWriter;

public class Property<T> {
    
    private PropertySchema<T> schema;
    private T value;
    
    public Property(PropertySchema<T> schema, T value) {
        this.schema = schema;
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public String getName() {
        return schema.getName();
    }
    
    public ValueType<T> getDataType() {
        return schema.getValueType();
    }
    
    public String getLabel() {
        return schema.getLabel();
    }
    
    public String getRemark() {
        return schema.getDesc();
    }
    
    public int getMaxRepeat() {
        return schema.getMaxRepeat();
    }
    
}
