package com.nimbuscommerce.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {
    private String orderId;
    private String orderStatus;
    private String productName;
    private String productPrice;
    private String estimatedDeliveryDate;
}
