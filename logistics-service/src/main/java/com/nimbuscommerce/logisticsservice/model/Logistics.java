package com.nimbuscommerce.logisticsservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Logistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String orderId;

    @NotBlank
    private String customerAddress;

    @NotBlank
    private String customerContact;

    @NotBlank
    private String status;

    @NotNull
    private LocalDateTime estimatedDeliveryDate;

}
