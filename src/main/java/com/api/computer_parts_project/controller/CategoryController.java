package com.api.computer_parts_project.controller;

import com.api.computer_parts_project.dto.CategoryDto;
import com.api.computer_parts_project.entity.CategoryEntity;
import com.api.computer_parts_project.service.CategoryService;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories()
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    // Get category by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(Helper.convertToDto(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get category by Name
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("name/{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name.toUpperCase())
                .map(category -> ResponseEntity.ok(Helper.convertToDto(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new category
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryEntity categoryEntity = Helper.convertToEntity(categoryDto);
        CategoryEntity createdCategory = categoryService.createCategory(categoryEntity);
        return new ResponseEntity<>(Helper.convertToDto(createdCategory), HttpStatus.CREATED);
    }

    // Update an existing category
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        CategoryEntity categoryEntity = Helper.convertToEntity(categoryDto);
        CategoryEntity updatedCategory = categoryService.updateCategory(id, categoryEntity);
        return ResponseEntity.ok(Helper.convertToDto(updatedCategory));
    }

    // Delete a category
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}