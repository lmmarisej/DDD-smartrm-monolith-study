package com.smartrm.smartrmmonolith.payment.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxPaymentResultNotificationDto {
    
    private String id;
    private String createTime;
    private String eventType;
    private String resourceType;
    private WxResourceDto resource;
    private String summary;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getEventType() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public String getResourceType() {
        return resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public WxResourceDto getResource() {
        return resource;
    }
    
    public void setResource(WxResourceDto resource) {
        this.resource = resource;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
