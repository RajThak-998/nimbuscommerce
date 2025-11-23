package com.nimbuscommerce.logisticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import order.events.OrderEvent;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {

    @KafkaListener(topics = "order", groupId = "logistics-service")
    public void consumeEvent(byte[] event) throws InvalidProtocolBufferException {
        OrderEvent orderEvent = OrderEvent.parseFrom(event);

    }
}
