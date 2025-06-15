package com.nurwebshop;

import com.nurwebshop.exception.ProductNotFoundException;
import com.nurwebshop.model.Product;
import com.nurwebshop.repository.ProductRepository;
import com.nurwebshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = List.of(
                new Product(1L, "Laptop", "Bra prestanda", 12999.99, "url", 10),
                new Product(2L, "Mus", "Gaming mus", 299.99, "url", 20)
        );

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById_Success() {
        Product product = new Product(1L, "Laptop", "Bra prestanda", 12999.99, "url", 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals("Laptop", result.getName());
        assertEquals(12999.99, result.getPrice());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(99L));
        verify(productRepository, times(1)).findById(99L);
    }
}
