package com.nimbuscommerce.orderservice.service;

import com.nimbuscommerce.orderservice.dto.OrderRequestDTO;
import com.nimbuscommerce.orderservice.dto.OrderResponseDTO;
import com.nimbuscommerce.orderservice.mapper.OrderMapper;
import com.nimbuscommerce.orderservice.model.Order;
import com.nimbuscommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponseDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderResponseDTO)
                .toList();
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order newOrder = orderRepository.save(
                orderMapper.toOrder(orderRequestDTO)
        );

        return orderMapper.toOrderResponseDTO(newOrder);

    }

    public OrderResponseDTO updateOrder(UUID id, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setProduct(orderRequestDTO.getProduct());
        order.setPrice(Double.parseDouble(orderRequestDTO.getPrice()));
        order.setQuantity(Integer.parseInt(orderRequestDTO.getQuantity()));
        order.setStatus("UPDATED");

        Order updatedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponseDTO(updatedOrder);

    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

}
