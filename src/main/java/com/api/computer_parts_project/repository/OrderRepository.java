package com.api.computer_parts_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.computer_parts_project.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);
    List<OrderEntity> findByStatus(String status);
}