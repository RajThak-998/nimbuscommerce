package com.nimbuscommerce.orderservice.grpc;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import order.LogisticsService;
import order.LogisticsService.LogisticsUpdateRequest;
import order.LogisticsService.LogisticsUpdateResponse;
import order.OrderLogisticsServiceGrpc;

@Slf4j
@GrpcService
public class OrderLogisticsGrpcService extends OrderLogisticsServiceGrpc.OrderLogisticsServiceImplBase {

    @Override
    public void updateLogisticsInfo(LogisticsUpdateRequest request,
                                     io.grpc.stub.StreamObserver<LogisticsService.LogisticsUpdateResponse> responseObserver) {
        // Implementation here
        boolean success = false;

        try{
            String deliveryId = request.getDeliveryId();
            String customerAddress = request.getCustomerAddress();
            String customerContact = request.getCustomerContact();
            String status = request.getStatus();
            String estimatedDelivery = request.getEstimatedDelivery();

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
