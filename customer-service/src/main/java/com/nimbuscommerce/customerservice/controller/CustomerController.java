package com.nimbuscommerce.customerservice.controller;

import com.nimbuscommerce.customerservice.dto.CustomerRequestDTO;
import com.nimbuscommerce.customerservice.dto.CustomerResponseDTO;
import com.nimbuscommerce.customerservice.model.Customer;
import com.nimbuscommerce.customerservice.repository.CustomerRepository;
import com.nimbuscommerce.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get All Customers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping
    @Operation(summary = "Create a new Customer")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.ok().body(customerResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Customer")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable("id") UUID id,
                                                              @Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.updateCustomer(id, customerRequestDTO);
        return ResponseEntity.ok().body(customerResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Customer")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") UUID id) {
        customerService.deleteCustomer(id);
        log.warn("Deleted customer with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
