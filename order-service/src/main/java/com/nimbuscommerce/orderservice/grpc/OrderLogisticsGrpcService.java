package com.nimbuscommerce.orderservice.grpc;

import com.nimbuscommerce.orderservice.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import order.LogisticsService;
import order.LogisticsService.LogisticsUpdateRequest;
import order.LogisticsService.LogisticsUpdateResponse;
import order.OrderLogisticsServiceGrpc;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class OrderLogisticsGrpcService extends OrderLogisticsServiceGrpc.OrderLogisticsServiceImplBase {

    private final KafkaProducer kafkaProducer;

    @Override
    public void updateLogisticsInfo(LogisticsUpdateRequest request,
                                     io.grpc.stub.StreamObserver<LogisticsService.LogisticsUpdateResponse> responseObserver) {
        // Implementation here
        boolean success = false;

        try{

            log.info("About to call KafkaProducer.sendNotificationEvent, deliveryId={}", request.getDeliveryId());
            kafkaProducer.sendNotificationEvent(request);
           log.info("send to the delivery notification from the grpc to kafka");

            log.info("Received logistics update for Delivery ID: {}", request.toString());

            success = true;

            LogisticsUpdateResponse response = LogisticsUpdateResponse.newBuilder()
                    .setSuccess(success)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch(Exception ex){
            LogisticsUpdateResponse response = LogisticsUpdateResponse.newBuilder()
                    .setSuccess(false)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
