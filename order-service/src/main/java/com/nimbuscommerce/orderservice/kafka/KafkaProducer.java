package com.nimbuscommerce.orderservice.kafka;

import com.nimbuscommerce.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notification.event.NotificationEvent;
import order.LogisticsService.LogisticsUpdateRequest;
import order.events.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendOrderEvent(Order order) {
        OrderEvent event = OrderEvent.newBuilder()
                .setOrderId(order.getId().toString())
                .setCustomerAddress(order.getAddress())
                .setCustomerContact(order.getEmail())
                .setStatus(order.getStatus())
                .build();

        try{
            kafkaTemplate.send("order", event.toByteArray());
            log.info("Sent OrderEvent to Kafka: {}", event.toString());
        } catch (Exception e){
            log.error("Failed to send OrderEvent to Kafka: {}", e.getMessage());
        }

    }

    public void sendNotificationEvent(LogisticsUpdateRequest request) {
        log.info("received the grpc info and about to send to the kafka notification topic");
        NotificationEvent event = NotificationEvent.newBuilder()
                .setDeliveryId(request.getDeliveryId())
                .setCustomerAddress(request.getCustomerAddress())
                .setCustomerContact(request.getCustomerContact())
                .setStatus(request.getStatus())
                .setEstimatedDelivery(request.getEstimatedDelivery())
                .build();

        try {
            kafkaTemplate.send("notification", event.toByteArray());
            log.info("Sent NotificationEvent to Kafka: {}", event.toString());
        } catch (Exception e){
            log.error("Failed to send NotificationEvent to Kafka: {}", e.getMessage());
        }
    }
}
