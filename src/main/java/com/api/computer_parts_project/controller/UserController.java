package com.api.computer_parts_project.controller;

import com.api.computer_parts_project.dto.UserDto;
import com.api.computer_parts_project.entity.UserEntity;
import com.api.computer_parts_project.service.UserService;
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
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers()
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(Helper.convertToDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by Username
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(user -> ResponseEntity.ok(Helper.convertToDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by Email
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(user -> ResponseEntity.ok(Helper.convertToDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get users by Role ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserDto>> getUsersByRoleId(@PathVariable Long roleId) {
        List<UserDto> users = userService.getUsersByRoleId(roleId)
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // Create a new user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserEntity userEntity = Helper.convertToEntity(userDto);
        UserEntity createdUser = userService.createUser(userEntity);
        return new ResponseEntity<>(Helper.convertToDto(createdUser), HttpStatus.CREATED);
    }

    // Update an existing user
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        UserEntity userEntity = Helper.convertToEntity(userDto);
        UserEntity updatedUser = userService.updateUser(id, userEntity);
        return ResponseEntity.ok(Helper.convertToDto(updatedUser));
    }

    // Delete a user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}