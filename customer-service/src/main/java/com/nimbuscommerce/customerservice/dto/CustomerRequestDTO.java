package com.nimbuscommerce.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank
    @Size(max = 100, message = "Name can have at most 100 characters")
    private String name;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
