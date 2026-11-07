package com.api.computer_parts_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.computer_parts_project.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategoryId(Long categoryId);
    List<ProductEntity> findByBrand(String brand);
}