package com.nimbuscommerce.customerservice.mapper;

import com.nimbuscommerce.customerservice.dto.CustomerRequestDTO;
import com.nimbuscommerce.customerservice.dto.CustomerResponseDTO;
import com.nimbuscommerce.customerservice.model.Customer;

public class CustomerMapper {

    public static CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId().toString());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setCreatedAt(customer.getCreatedAt().toString());
        dto.setUpdatedAt(customer.getUpdatedAt().toString());
        return dto;
    }

    public static Customer toCustomerModel(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer();
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setAddress(customerRequestDTO.getAddress());
        return customer;
    }

}
