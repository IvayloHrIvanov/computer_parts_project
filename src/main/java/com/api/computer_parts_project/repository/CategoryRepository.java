package com.api.computer_parts_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.computer_parts_project.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}