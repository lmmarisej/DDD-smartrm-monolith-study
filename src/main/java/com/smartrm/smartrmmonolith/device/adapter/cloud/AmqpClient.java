package com.smartrm.smartrmmonolith.device.adapter.cloud;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailure;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureCode;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.device.domain.event.PopSuccessEvent;
import com.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceId;
import com.smartrm.smartrmmonolith.device.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotVendingMachine;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import org.apache.commons.codec.binary.Base64;
import org.apache.qpid.jms.JmsConnection;
import org.apache.qpid.jms.JmsConnectionListener;
import org.apache.qpid.jms.message.JmsInboundMessageDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class AmqpClient {
    
    private final static Logger logger = LoggerFactory.getLogger(AmqpClient.class);
    //业务处理异步线程池，线程池参数可以根据您的业务特点调整，或者您也可以用其他异步方式处理接收到的消息。
    private final static ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue(50000));
    //iotInstanceId：实例ID。若是2021年07月30日之前（不含当日）开通的公共实例，请填空字符串。
    private static String iotInstanceId = "";
    // 指定单个进程启动的连接数
    // 单个连接消费速率有限，请参考使用限制，最大64个连接
    // 连接数和消费速率及rebalance相关，建议每500QPS增加一个连接
    private static int connectionCount = 4;
    private static JmsConnectionListener myJmsConnectionListener = new JmsConnectionListener() {
        /**
         * 连接成功建立。
         */
        @Override
        public void onConnectionEstablished(URI remoteURI) {
            logger.info("onConnectionEstablished, remoteUri:{}", remoteURI);
        }
        
        /**
         * 尝试过最大重试次数之后，最终连接失败。
         */
        @Override
        public void onConnectionFailure(Throwable error) {
            logger.error("onConnectionFailure, {}", error.getMessage());
        }
        
        /**
         * 连接中断。
         */
        @Override
        public void onConnectionInterrupted(URI remoteURI) {
            logger.info("onConnectionInterrupted, remoteUri:{}", remoteURI);
        }
        
        /**
         * 连接中断后又自动重连上。
         */
        @Override
        public void onConnectionRestored(URI remoteURI) {
            logger.info("onConnectionRestored, remoteUri:{}", remoteURI);
        }
        
        @Override
        public void onInboundMessage(JmsInboundMessageDispatch envelope) {
        }
        
        @Override
        public void onSessionClosed(Session session, Throwable cause) {
        }
        
        @Override
        public void onConsumerClosed(MessageConsumer consumer, Throwable cause) {
        }
        
        @Override
        public void onProducerClosed(MessageProducer producer, Throwable cause) {
        }
    };
    @Value("${aliyun.accessKey}")
    private String accessKey;
    @Value("${aliyun.accessSecret}")
    private String accessSecret;
    @Value("${aliyun.iot.consumerGroup}")
    private String consumerGroupId;
    //控制台服务端订阅中消费组状态页客户端ID一栏将显示clientId参数。
    //建议使用机器UUID、MAC地址、IP等唯一标识等作为clientId。便于您区分识别不同的客户端。
    private String clientId = UUID.randomUUID().toString();
    //${YourHost}为接入域名，请参见AMQP客户端接入说明文档。
    @Value("${aliyun.iot.amqp.host}")
    private String host;
    @Autowired
    private VendingMachineRepository machineRepository;
    @Autowired
    private DomainEventBus eventBus;
    private List<Connection> connections = new ArrayList<>();
    private MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessage(final Message message) {
            try {
                //1.收到消息之后一定要ACK。
                // 推荐做法：创建Session选择Session.AUTO_ACKNOWLEDGE，这里会自动ACK。
                // 其他做法：创建Session选择Session.CLIENT_ACKNOWLEDGE，这里一定要调message.acknowledge()来ACK。
                // message.acknowledge();
                //2.建议异步处理收到的消息，确保onMessage函数里没有耗时逻辑。
                // 如果业务处理耗时过程过长阻塞住线程，可能会影响SDK收到消息后的正常回调。
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        processMessage(message);
                    }
                });
            } catch (Exception e) {
                logger.error("submit task occurs exception ", e);
            }
        }
    };
    
    /**
     * 计算签名，password组装方法，请参见AMQP客户端接入说明文档。
     */
    private static String doSign(String toSignString, String secret, String signMethod)
            throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), signMethod);
        Mac mac = Mac.getInstance(signMethod);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(toSignString.getBytes());
        return Base64.encodeBase64String(rawHmac);
    }
    
    @PostConstruct
    public void start() throws Exception {
        
        //参数说明，请参见AMQP客户端接入说明文档。
        for (int i = 0; i < connectionCount; i++) {
            long timeStamp = System.currentTimeMillis();
            //签名方法：支持hmacmd5、hmacsha1和hmacsha256。
            String signMethod = "hmacsha1";
            
            //userName组装方法，请参见AMQP客户端接入说明文档。
            String userName = clientId + "-" + i + "|authMode=aksign"
                    + ",signMethod=" + signMethod
                    + ",timestamp=" + timeStamp
                    + ",authId=" + accessKey
                    + ",iotInstanceId=" + iotInstanceId
                    + ",consumerGroupId=" + consumerGroupId
                    + "|";
            //计算签名，password组装方法，请参见AMQP客户端接入说明文档。
            String signContent = "authId=" + accessKey + "&timestamp=" + timeStamp;
            String password = doSign(signContent, accessSecret, signMethod);
            String connectionUrl = "failover:(amqps://" + host + ":5671?amqp.idleTimeout=80000)"
                    + "?failover.reconnectDelay=30";
            
            Hashtable<String, String> hashtable = new Hashtable<>();
            hashtable.put("connectionfactory.SBCF", connectionUrl);
            hashtable.put("queue.QUEUE", "default");
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
            Context context = new InitialContext(hashtable);
            ConnectionFactory cf = (ConnectionFactory) context.lookup("SBCF");
            Destination queue = (Destination) context.lookup("QUEUE");
            // 创建连接。
            Connection connection = cf.createConnection(userName, password);
            connections.add(connection);
            
            ((JmsConnection) connection).addConnectionListener(myJmsConnectionListener);
            // 创建会话。
            // Session.CLIENT_ACKNOWLEDGE: 收到消息后，需要手动调用message.acknowledge()。
            // Session.AUTO_ACKNOWLEDGE: SDK自动ACK（推荐）。
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            
            connection.start();
            // 创建Receiver连接。
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(messageListener);
        }
        
        logger.info("amqp demo is started successfully, and will exit after 60s ");
        
    }
    
    @PreDestroy
    public void shutdown() throws InterruptedException {
        
        connections.forEach(c -> {
            try {
                c.close();
            } catch (JMSException e) {
                logger.error("failed to close connection", e);
            }
        });
        
        executorService.shutdown();
        
        if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.info("shutdown success");
        } else {
            logger.info("failed to handle messages");
        }
    }
    
    /**
     * 在这里处理您收到消息后的具体业务逻辑。
     */
    private void processMessage(Message message) {
        try {
            byte[] body = message.getBody(byte[].class);
            String content = new String(body);
            String topic = message.getStringProperty("topic");
            String messageId = message.getStringProperty("messageId");
            logger.info("receive message"
                    + ",\n topic = " + topic
                    + ",\n messageId = " + messageId
                    + ",\n content = " + content);
            ObjectMapper objectMapper = new ObjectMapper();
            if (topic.endsWith("/thing/event/DeviceFailure/post")) {
                JsonNode json = objectMapper.readTree(content);
                IoTDeviceFailure failure = objectMapper
                        .treeToValue(json.get("value"), IoTDeviceFailure.class);
                String productKey = json.get("productKey").asText();
                String deviceName = json.get("deviceName").asText();
                SlotVendingMachine machine = machineRepository
                        .getSlotVendingMachineByDeviceId(new IoTDeviceId(productKey, deviceName));
                DeviceFailureEvent event = new DeviceFailureEvent();
                event.setOrderId(Long.parseLong(failure.getOrderId(), 16));
                event.setMachineId(machine.getMachineId());
                event.setMachineType(machine.getType());
                DeviceFailure deviceFailure = new DeviceFailure();
                deviceFailure.setCode(DeviceFailureCode.DeviceError);
                Map<String, Object> failureData = Maps.newHashMap();
                failureData.put("errorCode", failure.getErrorCode());
                failureData.put("slot", failure.getSlotId());
                deviceFailure.setData(failureData);
                event.setFailure(deviceFailure);
                eventBus.post(event);
                message.acknowledge();
            } else if (topic.endsWith("/thing/event/PopSuccess/post")) {
                JsonNode json = objectMapper.readTree(content);
                IoTPopSuccessEvent iotEvent = objectMapper
                        .treeToValue(json.get("value"), IoTPopSuccessEvent.class);
                String productKey = json.get("productKey").asText();
                String deviceName = json.get("deviceName").asText();
                SlotVendingMachine machine = machineRepository
                        .getSlotVendingMachineByDeviceId(new IoTDeviceId(productKey, deviceName));
                PopSuccessEvent event = new PopSuccessEvent();
                event.setMachineId(machine.getMachineId());
                event.setOrderId(Long.parseLong(iotEvent.getOrderId(), 16));
                eventBus.post(event);
                message.acknowledge();
            }
        } catch (Exception e) {
            logger.error("processMessage occurs error ", e);
        }
    }
}
