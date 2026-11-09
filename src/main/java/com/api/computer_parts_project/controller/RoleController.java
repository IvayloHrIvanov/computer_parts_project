package com.api.computer_parts_project.controller;

import com.api.computer_parts_project.dto.RoleDto;
import com.api.computer_parts_project.entity.RoleEntity;
import com.api.computer_parts_project.service.RoleService;
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
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Get all roles
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles()
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    // Get role by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(role -> ResponseEntity.ok(Helper.convertToDto(role)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get role by Name
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDto> getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name.toUpperCase())
                .map(role -> ResponseEntity.ok(Helper.convertToDto(role)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new role
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) {
        RoleEntity roleEntity = Helper.convertToEntity(roleDto);
        RoleEntity createdRole = roleService.createRole(roleEntity);
        return new ResponseEntity<>(Helper.convertToDto(createdRole), HttpStatus.CREATED);
    }

    // Update an existing role
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        RoleEntity roleEntity = Helper.convertToEntity(roleDto);
        RoleEntity updatedRole = roleService.updateRole(id, roleEntity);
        return ResponseEntity.ok(Helper.convertToDto(updatedRole));
    }

    // Delete a role
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}