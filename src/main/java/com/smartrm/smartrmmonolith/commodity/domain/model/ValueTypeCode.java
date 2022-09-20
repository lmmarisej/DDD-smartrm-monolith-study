package com.smartrm.smartrmmonolith.commodity.domain.model;

public enum ValueTypeCode {
    
    //字符串
    STRING,
    //整数
    INTEGER,
    //浮点数
    FLOAT,
    //货币
    CURRENCY,
    //日期，对应LocalDate
    DATE,
    //日期时间，对应LocalDateTime
    DATETIME,
    //图片url
    IMAGE_URL,
    //字典
    MAP;
}
