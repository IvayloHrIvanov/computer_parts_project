package com.api.computer_parts_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.computer_parts_project.entity.OrderItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findByOrderId(Long orderId);
}