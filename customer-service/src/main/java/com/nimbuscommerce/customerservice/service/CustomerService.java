package com.nimbuscommerce.customerservice.service;

import com.nimbuscommerce.customerservice.dto.CustomerRequestDTO;
import com.nimbuscommerce.customerservice.dto.CustomerResponseDTO;
import com.nimbuscommerce.customerservice.exception.EmailAlreadyExistsException;
import com.nimbuscommerce.customerservice.mapper.CustomerMapper;
import com.nimbuscommerce.customerservice.model.Customer;
import com.nimbuscommerce.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        if(customerRepository.existsByEmail(customerRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Customer customer = customerRepository.save(
                CustomerMapper.toCustomerModel(customerRequestDTO)
        );

        return CustomerMapper.toCustomerResponseDTO(customer);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper::toCustomerResponseDTO)
                .toList();
    }

    public CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        if(customerRepository.existsByEmailAndIdNot(customer.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email already exists: " + customerRequestDTO.getEmail());
        }

        customer.setName(customerRequestDTO.getName());
        customer.setAddress(customerRequestDTO.getAddress());
        customer.setEmail(customerRequestDTO.getEmail());

        return CustomerMapper.toCustomerResponseDTO(customerRepository.save(customer));
    }

    public void deleteCustomer(UUID id) {customerRepository.deleteById(id);}

}
