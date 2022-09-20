package com.smartrm.smartrmmonolith.commodity.domain.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.smartrm.smartrmmonolith.commodity.infrastructure.CommodityError.CommodityNoSuchProperty;
import static com.smartrm.smartrmmonolith.commodity.infrastructure.CommodityError.CommodityPropertyReachMaxRepeat;

public class Commodity implements Serializable {
    
    public static final String PROPERTY_NAME_ID = "commodityId";
    public static final String PROPERTY_NAME_NAME = "commodityName";
    private static final Logger LOGGER = LoggerFactory.getLogger(Commodity.class);
    private CommoditySchema schema;
    
    private Multimap<String, Property> data = ArrayListMultimap.create();
    
    public Commodity(CommoditySchema schema) {
        this.schema = schema;
    }
    
    public <T> void setProperty(Property<T> property) {
        if (data.get(property.getName()).size() >= property.getMaxRepeat()) {
            throw new DomainException(CommodityPropertyReachMaxRepeat);
        } else {
            data.put(property.getName(), property);
        }
    }
    
    public <T> void setValue(String name, T value) {
        PropertySchema propertySchema = schema.getPropertySchema(name);
        if (propertySchema == null) {
            throw new DomainException(CommodityNoSuchProperty);
        } else {
            Property<T> property = new Property<>(propertySchema, value);
            setProperty(property);
        }
    }
    
    public <T> Property<T> getSingleProperty(String name) {
        if (!data.get(name).isEmpty()) {
            return data.get(name).iterator().next();
        } else {
            return null;
        }
    }
    
    public String getAsString(String name) {
        return this.<String>getSingleProperty(name).getValue();
    }
    
    public BigDecimal getAsBigDecimal(String name) {
        return this.<BigDecimal>getSingleProperty(name).getValue();
    }
    
    public Long getAsLong(String name) {
        return this.<Long>getSingleProperty(name).getValue();
    }
    
    public LocalDate getAsLocalDate(String name) {
        return this.<LocalDate>getSingleProperty(name).getValue();
    }
    
    public LocalDateTime getAsLocalDateTime(String name) {
        return this.<LocalDateTime>getSingleProperty(name).getValue();
    }
    
    public ImageUrl getAsImageUrl(String name) {
        return this.<ImageUrl>getSingleProperty(name).getValue();
    }
    
    public Collection<Property> getMultiProperty(String name) {
        return data.get(name);
    }
    
    public <T> List<T> getAsList(String name) {
        return data.get(name).stream().map(p -> ((Property<T>) p).getValue())
                .collect(Collectors.toList());
    }
    
    public Collection<Property> getAllProperties() {
        return data.values();
    }
    
    public String getCommodityId() {
        return this.<String>getSingleProperty(PROPERTY_NAME_ID).getValue();
    }
    
    public String getCommodityName() {
        return this.<String>getSingleProperty(PROPERTY_NAME_NAME).getValue();
    }
    
}
