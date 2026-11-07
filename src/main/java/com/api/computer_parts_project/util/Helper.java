package com.api.computer_parts_project.util;

import com.api.computer_parts_project.dto.*;
import com.api.computer_parts_project.entity.*;

public class Helper {

    // --- User Mappers ---
    public static UserDto convertToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPasswordHash(entity.getPasswordHash());
        dto.setRoleId(entity.getRoleId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static UserEntity convertToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPasswordHash(dto.getPasswordHash());
        entity.setRoleId(dto.getRoleId());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

    // --- Role Mappers ---
    public static RoleDto convertToDto(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static RoleEntity convertToEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    // --- Product Mappers ---
    public static ProductDto convertToDto(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setPrice(entity.getPrice());
        dto.setStockQuantity(entity.getStockQuantity());
        dto.setCategoryId(entity.getCategoryId());
        dto.setSpecifications(entity.getSpecifications());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static ProductEntity convertToEntity(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setPrice(dto.getPrice());
        entity.setStockQuantity(dto.getStockQuantity());
        entity.setCategoryId(dto.getCategoryId());
        entity.setSpecifications(dto.getSpecifications());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

    // --- OrderItem Mappers ---
    public static OrderItemDto convertToDto(OrderItemEntity entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrderId());
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        return dto;
    }

    public static OrderItemEntity convertToEntity(OrderItemDto dto) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(dto.getId());
        entity.setOrderId(dto.getOrderId());
        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice());
        return entity;
    }

    // --- Order Mappers ---
    public static OrderDto convertToDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setStatus(entity.getStatus());
        dto.setShippingAddress(entity.getShippingAddress());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static OrderEntity convertToEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setStatus(dto.getStatus());
        entity.setShippingAddress(dto.getShippingAddress());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

    // --- Category Mappers ---
    public static CategoryDto convertToDto(CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static CategoryEntity convertToEntity(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}