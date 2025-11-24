package com.nimbuscommerce.logisticsservice.repository;

import com.nimbuscommerce.logisticsservice.model.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LogisticsRepository extends JpaRepository<Logistics, UUID> {
    Optional<Logistics> findByOrderId(String orderId);
}
