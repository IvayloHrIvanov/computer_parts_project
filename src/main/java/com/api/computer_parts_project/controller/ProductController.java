package com.api.computer_parts_project.controller;

import com.api.computer_parts_project.dto.ProductDto;
import com.api.computer_parts_project.entity.ProductEntity;
import com.api.computer_parts_project.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts()
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(Helper.convertToDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get products by Category ID
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductDto> products = productService.getProductsByCategoryId(categoryId)
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    // Get products by Brand
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductDto>> getProductsByBrand(@PathVariable String brand) {
        List<ProductDto> products = productService.getProductsByBrand(brand)
                .stream()
                .map(Helper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    // Create a new product
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductEntity productEntity = Helper.convertToEntity(productDto);
        ProductEntity createdProduct = productService.createProduct(productEntity);
        return new ResponseEntity<>(Helper.convertToDto(createdProduct), HttpStatus.CREATED);
    }

    // Update an existing product
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        ProductEntity productEntity = Helper.convertToEntity(productDto);
        ProductEntity updatedProduct = productService.updateProduct(id, productEntity);
        return ResponseEntity.ok(Helper.convertToDto(updatedProduct));
    }

    // Delete a product
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}