package com.nimbuscommerce.customerservice.grpc;

import com.nimbuscommerce.customerservice.model.Customer;
import com.nimbuscommerce.customerservice.repository.CustomerRepository;
import customer.CustomerRequest;
import customer.CustomerResponse;
import customer.CustomerServiceGrpc.CustomerServiceImplBase;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@GrpcService
public class CustomerGrpcService extends CustomerServiceImplBase {

    private final CustomerRepository customerRepository;

    @Override
    public void getCustomerDetails(CustomerRequest request,
                                   StreamObserver<CustomerResponse> responseObserver) {
        // Implementation here
        try {
            // 1\) Parse UUID
            UUID customerId;
            try {
                customerId = UUID.fromString(request.getCustomerId());
            } catch (IllegalArgumentException ex) {
                responseObserver.onError(
                        Status.INVALID_ARGUMENT
                                .withDescription("Invalid customerId, must be UUID")
                                .withCause(ex)
                                .asRuntimeException()
                );
                return;
            }

            // 2\) Load from DB
            Optional<Customer> optCustomer = customerRepository.findById(customerId);
            if (optCustomer.isEmpty()) {
                responseObserver.onError(
                        Status.NOT_FOUND
                                .withDescription("Customer not found for id: " + customerId)
                                .asRuntimeException()
                );
                return;
            }

            Customer customer = optCustomer.get();

            // 3\) Map to proto response
            CustomerResponse response = CustomerResponse.newBuilder()
                    .setName(customer.getName())
                    .setEmail(customer.getEmail())
                    .setAddress(customer.getAddress())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Unexpected error while fetching customer")
                            .withCause(e)
                            .asRuntimeException()
            );
        }
    }
}
