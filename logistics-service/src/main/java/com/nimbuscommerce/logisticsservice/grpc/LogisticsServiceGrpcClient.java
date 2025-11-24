package com.nimbuscommerce.logisticsservice.grpc;

import com.nimbuscommerce.logisticsservice.model.Logistics;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import order.LogisticsService.LogisticsUpdateRequest;
import order.LogisticsService.LogisticsUpdateResponse;
import order.OrderLogisticsServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogisticsServiceGrpcClient {
    private final OrderLogisticsServiceGrpc.OrderLogisticsServiceBlockingStub blockingStub;

    public LogisticsServiceGrpcClient(@Value("${order.service.address:localhost}") String serverAddress,
                                      @Value("${order.service.grpc.port:9003}") int serverPort){
        log.info("connecting to Order GRPC service at {}:{}", serverAddress, serverPort);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingStub = OrderLogisticsServiceGrpc.newBlockingStub(channel);
    }

     public LogisticsUpdateResponse updateLogisticsInfo(Logistics logistics){
        LogisticsUpdateRequest request = LogisticsUpdateRequest.newBuilder()
                .setDeliveryId(logistics.getId().toString())
                .setCustomerAddress(logistics.getCustomerAddress())
                .setCustomerContact(logistics.getCustomerContact())
                .setStatus(logistics.getStatus())
                .setEstimatedDelivery(logistics.getEstimatedDeliveryDate().toString())
                .build();

        LogisticsUpdateResponse response = blockingStub.updateLogisticsInfo(request);
        log.info("Received LogisticsUpdateResponse from Order Service: {}", response.toString());
        return response;
     }
}
