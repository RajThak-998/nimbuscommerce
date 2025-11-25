package com.nimbuscommerce.notificationservice.kafka;


import com.google.protobuf.InvalidProtocolBufferException;
import notification.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(kafkaConsumer.class);

    @KafkaListener(topics="notification", groupId = "notification-service")
    public void consumeEvent(byte[] event){
        try{
            NotificationEvent notificationEvent = NotificationEvent.parseFrom(event);
            log.info("Received notification event: {}", notificationEvent.toString());
            log.info("Notification service is running successfully.");
        } catch (InvalidProtocolBufferException e) {
            log.error("Error Deserializing NotificationEvent: {}", e.getMessage());
        }
    }
}
