package com.api.computer_parts_project.controller;

import com.api.computer_parts_project.dto.OrderDto;
import com.api.computer_parts_project.entity.OrderEntity;
import com.api.computer_parts_project.service.OrderService;
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
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders()
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> ResponseEntity.ok(Helper.convertToDto(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get orders by User ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderDto> orders = orderService.getOrdersByUserId(userId)
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    // Create a new order
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderEntity orderEntity = Helper.convertToEntity(orderDto);
        OrderEntity createdOrder = orderService.createOrder(orderEntity);
        return new ResponseEntity<>(Helper.convertToDto(createdOrder), HttpStatus.CREATED);
    }

    // Update an existing order
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        OrderEntity orderEntity = Helper.convertToEntity(orderDto);
        OrderEntity updatedOrder = orderService.updateOrder(id, orderEntity);
        return ResponseEntity.ok(Helper.convertToDto(updatedOrder));
    }

    // Delete an order
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}