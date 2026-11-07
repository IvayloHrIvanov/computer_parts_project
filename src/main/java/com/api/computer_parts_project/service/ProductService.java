package com.api.computer_parts_project.service;

import com.api.computer_parts_project.entity.ProductEntity;
import com.api.computer_parts_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Get products by Category ID
    public List<ProductEntity> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // Get products by Brand
    public List<ProductEntity> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    // Create a new product
    public ProductEntity createProduct(ProductEntity product) {
        if (product.getCreatedAt() == null) {
            product.setCreatedAt(OffsetDateTime.now());
        }
        return productRepository.save(product);
    }

    // Update an existing product
    public ProductEntity updateProduct(Long id, ProductEntity productDetails) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productDetails.getName());
        product.setBrand(productDetails.getBrand());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategoryId(productDetails.getCategoryId());
        product.setSpecifications(productDetails.getSpecifications());

        return productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}