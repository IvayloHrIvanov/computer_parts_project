package com.api.computer_parts_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.computer_parts_project.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRoleId(Long roleId);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}