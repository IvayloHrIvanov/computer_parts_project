package com.api.computer_parts_project.service;

import com.api.computer_parts_project.entity.OrderItemEntity;
import com.api.computer_parts_project.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Get all order items
    public List<OrderItemEntity> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get order item by ID
    public Optional<OrderItemEntity> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    // Get items by Order ID
    public List<OrderItemEntity> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    // Create or save an order item
    public OrderItemEntity createOrderItem(OrderItemEntity orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Update an existing order item
    public OrderItemEntity updateOrderItem(Long id, OrderItemEntity orderItemDetails) {
        OrderItemEntity orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));

        orderItem.setOrderId(orderItemDetails.getOrderId());
        orderItem.setProductId(orderItemDetails.getProductId());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setUnitPrice(orderItemDetails.getUnitPrice());

        return orderItemRepository.save(orderItem);
    }

    // Delete an order item
    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new RuntimeException("OrderItem not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}