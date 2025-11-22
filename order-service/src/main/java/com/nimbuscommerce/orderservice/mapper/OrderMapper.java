package com.nimbuscommerce.orderservice.mapper;

import com.nimbuscommerce.orderservice.dto.OrderRequestDTO;
import com.nimbuscommerce.orderservice.dto.OrderResponseDTO;
import com.nimbuscommerce.orderservice.grpc.CustomerServiceGrpcClient;
import com.nimbuscommerce.orderservice.model.Order;
import customer.CustomerResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CustomerServiceGrpcClient customerServiceGrpcClient;

    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        // set sample value for now
        CustomerResponse response = customerServiceGrpcClient.getCustomerDetails(orderRequestDTO.getCustomerId());
        order.setCustomerId(UUID.fromString(orderRequestDTO.getCustomerId()));
        order.setAddress(response.getAddress());
        order.setEmail(response.getEmail());
        order.setProduct(orderRequestDTO.getProduct());
        order.setPrice(Double.parseDouble(orderRequestDTO.getPrice()));
        order.setQuantity(Integer.parseInt(orderRequestDTO.getQuantity()));
        return order;
    }

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(order.getId().toString());
        orderResponseDTO.setProductName(order.getProduct());
        orderResponseDTO.setOrderStatus(order.getStatus());
        orderResponseDTO.setEstimatedDeliveryDate(order.getEstimatedDeliveryDate().toString());
        orderResponseDTO.setProductPrice(String.valueOf(order.getPrice()));
        return orderResponseDTO;
    }
}
