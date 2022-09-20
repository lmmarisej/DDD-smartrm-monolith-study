package com.smartrm.smartrmmonolith.event;

import com.smartrm.smartrmmonolith.infracore.event.DomainEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class TestEventHandler implements DomainEventHandler<TestEvent> {
    
    private static Logger LOGGER = LoggerFactory.getLogger(TestEventHandler.class);
    
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        LOGGER.info("test event");
    }
}
