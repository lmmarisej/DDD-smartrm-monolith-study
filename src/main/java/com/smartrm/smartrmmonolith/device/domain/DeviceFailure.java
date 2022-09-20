package com.smartrm.smartrmmonolith.device.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
public class DeviceFailure implements Serializable {
    
    DeviceFailureCode code;
    Map<String, Object> data = new HashMap<>();
    
    public DeviceFailureCode getCode() {
        return code;
    }
    
    public void setCode(DeviceFailureCode code) {
        this.code = code;
    }
    
    public Map<String, Object> getData() {
        return data;
    }
    
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    
    public DeviceFailure withData(String key, Object data) {
        this.data.put(key, data);
        return this;
    }
}
