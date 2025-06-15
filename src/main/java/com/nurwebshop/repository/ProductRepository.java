package com.nurwebshop.repository;

import com.nurwebshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        // Exempelprodukter
        products.add(new Product(1L, "Laptop", "Bra prestanda", 12999.99, "https://example.com/laptop.jpg", 10));
        products.add(new Product(2L, "Mus", "Trådlös gaming mus", 299.99, "https://example.com/mouse.jpg", 25));
        products.add(new Product(3L, "Tangentbord", "Bakgrundsbelyst tangentbord", 499.99, "https://example.com/keyboard.jpg", 15));
        products.add(new Product(4L, "Skärm", "27\" 4K bildskärm", 2999.00, "https://example.com/monitor.jpg", 8));
        products.add(new Product(5L, "Webbkamera", "HD-kamera för videomöten", 799.00, "https://example.com/webcam.jpg", 20));
        products.add(new Product(6L, "Headset", "Trådlöst headset med mikrofon", 599.00, "https://example.com/headset.jpg", 18));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
