package com.smartrm.smartrmmonolith.trade.adapter.eventhandler;

import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventHandler;
import com.smartrm.smartrmmonolith.trade.application.AppTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description: 设备故障事件处理器，处理执行订单时的设备故障
 */
@Component
public class DeviceFailureEventHandler implements DomainEventHandler<DeviceFailureEvent> {
    
    private static Logger LOGGER = LoggerFactory.getLogger(DeviceFailureEventHandler.class);
    
    @Autowired
    private AppTradeService tradeService;
    
    @Override
    public void onApplicationEvent(DeviceFailureEvent deviceFailureEvent) {
        LOGGER.info("receive event:{},{},{},{}", deviceFailureEvent.getMachineId(),
                deviceFailureEvent.getMachineType(), deviceFailureEvent.getOrderId(),
                deviceFailureEvent.getFailure());
        tradeService.onDeviceFailure(deviceFailureEvent);
    }
    
}
