package com.nimbuscommerce.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderRequestDTO {

    @NotBlank
    private String customerId;

    @NotBlank
    private String product;

    @Size(min = 1, message = "Quantity must be at least 1")
    private String quantity;

    @NotBlank
    private String price;

}
