package com.smartrm.smartrmmonolith.commodity.domain.model;

public class CriteriaField {
    
    /**
     * 字段英文名，驼峰格式
     */
    private String field;
    /**
     * 字段名称，中文名称
     */
    private String fieldName;
    /**
     * 字段别名，下划线格式
     */
    private String fieldAlias;
    
    /**
     * 字段值是否支持用户输入
     */
    private boolean fieldValueCustomizedEnabled;
    /**
     * 字段值是否支持从系统值中选择
     */
    private boolean fieldValueSystemEnabled;
    
    public String getField() {
        return field;
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String getFieldAlias() {
        return fieldAlias;
    }
    
    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias;
    }
    
    public boolean isFieldValueCustomizedEnabled() {
        return fieldValueCustomizedEnabled;
    }
    
    public void setFieldValueCustomizedEnabled(boolean fieldValueCustomizedEnabled) {
        this.fieldValueCustomizedEnabled = fieldValueCustomizedEnabled;
    }
    
    public boolean isFieldValueSystemEnabled() {
        return fieldValueSystemEnabled;
    }
    
    public void setFieldValueSystemEnabled(boolean fieldValueSystemEnabled) {
        this.fieldValueSystemEnabled = fieldValueSystemEnabled;
    }
}
