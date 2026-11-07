package com.api.computer_parts_project.service;

import com.api.computer_parts_project.entity.RoleEntity;
import com.api.computer_parts_project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Get all roles
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get role by ID
    public Optional<RoleEntity> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Get role by Name
    public Optional<RoleEntity> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    // Create a new role
    public RoleEntity createRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    // Update an existing role
    public RoleEntity updateRole(Long id, RoleEntity roleDetails) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        role.setName(roleDetails.getName());

        return roleRepository.save(role);
    }

    // Delete a role
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
}