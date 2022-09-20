package com.smartrm.smartrmmonolith.device.domain.iot;

/**
 * @author: yoda
 * @description: 阿里云iot平台设备id
 */
public class IoTDeviceId {
    
    private final String productKey;
    private final String deviceName;
    
    public IoTDeviceId(String productKey, String deviceName) {
        this.productKey = productKey;
        this.deviceName = deviceName;
    }
    
    public String productKey() {
        return this.productKey;
    }
    
    public String deviceName() {
        return this.deviceName;
    }
}
