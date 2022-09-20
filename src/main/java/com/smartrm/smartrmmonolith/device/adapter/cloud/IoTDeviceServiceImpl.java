package com.smartrm.smartrmmonolith.device.adapter.cloud;

import com.aliyun.teaopenapi.models.Config;
import com.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceId;
import com.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: yoda
 * @description:
 */
@Service
public class IoTDeviceServiceImpl implements IoTDeviceService {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(IoTDeviceServiceImpl.class);
    
    @Value("${aliyun.accessKey}")
    private String accessKey;
    
    @Value("${aliyun.accessSecret}")
    private String accessSecret;
    
    @Value("${aliyun.iot.endpoint}")
    private String endpoint;
    
    private com.aliyun.iot20180120.Client client;
    
    @PostConstruct
    public void init() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKey)
                .setAccessKeySecret(accessSecret);
        config.endpoint = endpoint;
        client = new com.aliyun.iot20180120.Client(config);
    }
    
    public void popCommodity(IoTDeviceId deviceId, int slotId, long orderId)
            throws Exception {
//    Map<String, Object> args = Maps.newHashMap();
//    args.put("slotId", slotId);
//    args.put("orderId", Long.toHexString(orderId));
//    InvokeThingServiceRequest invokeThingServiceRequest = new InvokeThingServiceRequest()
//        .setProductKey(deviceId.productKey())
//        .setDeviceName(deviceId.deviceName())
//        .setIdentifier("popCommodity")
//        .setArgs(new ObjectMapper().writeValueAsString(args));
//    InvokeThingServiceResponse response = client.invokeThingService(invokeThingServiceRequest);
//    if (!response.body.success) {
//      LOGGER.error("fail to request:" + response.body.errorMessage + "," + orderId);
//      throw new DomainException(DeviceError.IoTRequestFail).withMsg(response.body.errorMessage);
//    }
    }
    
}
