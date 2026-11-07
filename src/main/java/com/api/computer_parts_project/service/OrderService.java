package com.api.computer_parts_project.service;

import com.api.computer_parts_project.entity.OrderEntity;
import com.api.computer_parts_project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Get orders by User ID
    public List<OrderEntity> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Get orders by Status
    public List<OrderEntity> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    // Create a new order
    public OrderEntity createOrder(OrderEntity order) {
        if (order.getCreatedAt() == null) {
            order.setCreatedAt(OffsetDateTime.now());
        }
        return orderRepository.save(order);
    }

    // Update an existing order
    public OrderEntity updateOrder(Long id, OrderEntity orderDetails) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setUserId(orderDetails.getUserId());
        order.setTotalAmount(orderDetails.getTotalAmount());
        order.setStatus(orderDetails.getStatus());
        order.setShippingAddress(orderDetails.getShippingAddress());

        return orderRepository.save(order);
    }

    // Delete an order
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}