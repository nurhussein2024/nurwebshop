package com.nurwebshop.service;

import com.nurwebshop.exception.ProductNotFoundException;
import com.nurwebshop.model.Product;
import com.nurwebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Hämta alla produkter
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Hämta en produkt via ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produkten med ID " + id + " hittades inte"));
    }
}