package com.nimbuscommerce.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String createdAt;
    private String updatedAt;
}
