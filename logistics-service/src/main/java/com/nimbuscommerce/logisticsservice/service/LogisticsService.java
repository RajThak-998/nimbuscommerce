package com.nimbuscommerce.logisticsservice.service;

import com.nimbuscommerce.logisticsservice.grpc.LogisticsServiceGrpcClient;
import com.nimbuscommerce.logisticsservice.model.Logistics;
import com.nimbuscommerce.logisticsservice.repository.LogisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.events.OrderEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogisticsService {
    private final LogisticsRepository logisticsRepository;
    private final LogisticsServiceGrpcClient logisticsServiceGrpcClient;

    public void processOrderEvent(OrderEvent event) {
        Optional<Logistics> existingLogistics = logisticsRepository.findByOrderId(event.getOrderId());
        Logistics logistics = existingLogistics.orElseGet(Logistics::new);
        logistics.setOrderId(event.getOrderId());
        logistics.setCustomerAddress(event.getCustomerAddress());
        logistics.setCustomerContact(event.getCustomerContact());
        logistics.setStatus(event.getStatus());

        if(!event.getStatus().equals("DELETED")){
            logistics.setEstimatedDeliveryDate(calculateEstimatedDeliveryDate(event));
        }

        logisticsRepository.save(logistics);
        processLogisticsInfo(logistics);
    }

    public void processLogisticsInfo(Logistics logistics) {
        try {
            order.LogisticsService.LogisticsUpdateResponse response =
                    logisticsServiceGrpcClient.updateLogisticsInfo(logistics);
            log.info("Logistics info updated in Order Service: {}", response.getSuccess());
        } catch (Exception e) {
            log.error("Failed to update logistics info in Order Service", e);
        }
    }

    private LocalDateTime calculateEstimatedDeliveryDate(OrderEvent event){
        return LocalDateTime.now().plusDays(7);
    }
}
