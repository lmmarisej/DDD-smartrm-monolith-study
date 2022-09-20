package com.smartrm.smartrmmonolith.infracore.event.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smartrm.smartrmmonolith.infracore.event.DomainEvent;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventHandler;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author: yoda
 * @description: 事件总线的简单实现，只能在单体架构中应用
 */
@Component
public class SimpleEventBusImpl implements DomainEventBus, ApplicationContextAware {

//  @Autowired
//  private ApplicationEventPublisher eventBus;
    
    private static Logger LOGGER = LoggerFactory.getLogger(SimpleEventBusImpl.class);
    //  private AnnotationConfigApplicationContext context;
    private ConfigurableApplicationContext applicationContext;
    @Value("${kafka.server}")
    private String kafkaServer;
    @Value("${kafka.retries}")
    private Integer retries;
    @Value("${kafka.batch.size}")
    private Integer batchSize;
    @Value("${kafka.linger.ms}")
    private Integer lingerMs;
    @Value("${kafka.buffer.memory}")
    private Integer bufferMemory;
    private Producer<String, String> producer;
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    
    @PostConstruct
    private void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServer);
        props.put("acks", "all");
        props.put("retries", retries);
        props.put("batch.size", batchSize);
        props.put("linger.ms", lingerMs);
        props.put("buffer.memory", bufferMemory);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        producer = new KafkaProducer<>(props);
    }
    
    @Override
    public void post(DomainEvent event) {
        applicationContext.publishEvent(event);
        try {
            String message = objectMapper.writeValueAsString(event);
            producer.send(new ProducerRecord<>(event.getEventName(), event.key(), message));
        } catch (Exception e) {
            LOGGER.error("error when store event", e);
        }
        
    }
    
    @Override
    public void register(DomainEventHandler handler) {
//    applicationContext.addApplicationListener(handler);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }
    
}
