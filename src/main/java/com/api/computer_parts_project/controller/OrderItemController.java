package com.api.computer_parts_project.controller;

import com.api.computer_parts_project.dto.OrderItemDto;
import com.api.computer_parts_project.entity.OrderItemEntity;
import com.api.computer_parts_project.service.OrderItemService;
import com.api.computer_parts_project.util.Helper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Get all order items
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orderItems = orderItemService.getAllOrderItems()
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderItems);
    }

    // Get order item by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id)
                .map(orderItem -> ResponseEntity.ok(Helper.convertToDto(orderItem)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get order items by Order ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItemDto> orderItems = orderItemService.getOrderItemsByOrderId(orderId)
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderItems);
    }

    // Create a new order item
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = Helper.convertToEntity(orderItemDto);
        OrderItemEntity createdOrderItem = orderItemService.createOrderItem(orderItemEntity);
        return new ResponseEntity<>(Helper.convertToDto(createdOrderItem), HttpStatus.CREATED);
    }

    // Update an existing order item
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable Long id, @Valid @RequestBody OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = Helper.convertToEntity(orderItemDto);
        OrderItemEntity updatedOrderItem = orderItemService.updateOrderItem(id, orderItemEntity);
        return ResponseEntity.ok(Helper.convertToDto(updatedOrderItem));
    }

    // Delete an order item
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}