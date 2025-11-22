package com.nimbuscommerce.orderservice.grpc;

import customer.CustomerRequest;
import customer.CustomerResponse;
import customer.CustomerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceGrpcClient {
    private final CustomerServiceGrpc.CustomerServiceBlockingStub blockingStub;

    public CustomerServiceGrpcClient(@Value("${customer.service.address:localhost}") String serverAddress,
                                     @Value("${customer.service.grpc.port:9002}") int serverPort) {
        log.info("Connecting to Customer GRPC service at {}:{}", serverAddress, serverPort);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingStub = CustomerServiceGrpc.newBlockingStub(channel);
    }

    public CustomerResponse getCustomerDetails(String customerId) {
        CustomerRequest request = CustomerRequest.newBuilder().setCustomerId(customerId).build();
        CustomerResponse response = blockingStub.getCustomerDetails(request);
        log.info("Received CustomerResponse from Customer Service: {}", response.toString());
        return response;
    }
}

