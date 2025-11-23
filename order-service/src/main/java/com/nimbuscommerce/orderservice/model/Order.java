package com.nimbuscommerce.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private UUID customerId;

    @NotBlank
    private String address;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String product;

    @Size(min = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull
    private double price;

    @NotBlank
    private String status = "CREATED";

    @Column(updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Object getEstimatedDeliveryDate() {
        return createdAt.plusDays(7);
    }
}
