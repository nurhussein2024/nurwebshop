package com.nurwebshop;

import com.nurwebshop.controller.ProductController;
import com.nurwebshop.model.Product;
import com.nurwebshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    // Testar hämtning av alla produkter
    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = List.of(
                new Product(1L, "Laptop", "Bra prestanda", 12999.99, "bild1.jpg", 10),
                new Product(2L, "Mus", "Gaming mus", 299.99, "bild2.jpg", 20)
        );

        when(productService.getAllProducts()).thenReturn(mockProducts);

        List<Product> result = productController.getAllProducts();
        assertEquals(2, result.size());
        verify(productService, times(1)).getAllProducts();
    }

    // Testar hämtning av en produkt via ID
    @Test
    void testGetProductById() {
        Product mockProduct = new Product(1L, "Laptop", "Bra prestanda", 12999.99, "bild1.jpg", 10);

        when(productService.getProductById(1L)).thenReturn(mockProduct);

        Product result = productController.getProductById(1L);
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(productService, times(1)).getProductById(1L);
    }
}