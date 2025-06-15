package com.nurwebshop;

import com.nurwebshop.exception.ProductNotFoundException;
import com.nurwebshop.model.*;
import com.nurwebshop.repository.OrderRepository;
import com.nurwebshop.repository.ProductRepository;
import com.nurwebshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    public void testCreateOrder_Success() {
        OrderItem item1 = new OrderItem(1L, 2, 0.0);
        OrderItem item2 = new OrderItem(2L, 1, 0.0);
        List<OrderItem> items = List.of(item1, item2);

        CustomerInfo customer = new CustomerInfo("Ali", "Stockholm", "ali@example.com");

        Product product1 = new Product(1L, "Laptop", "Bra", 1000.0, "url", 5);
        Product product2 = new Product(2L, "Mus", "Gaming", 100.0, "url", 10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        Order result = orderService.createOrder(customer, items);

        assertNotNull(result.getId());
        assertEquals(2100.0, result.getTotalAmount());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testCreateOrder_ProductNotFound() {
        OrderItem item = new OrderItem(999L, 1, 0.0);
        CustomerInfo customer = new CustomerInfo("Ali", "Stockholm", "ali@example.com");

        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.createOrder(customer, List.of(item)));
    }

    @Test
    public void testCreateOrder_InvalidQuantity() {
        Product product = new Product(1L, "Laptop", "Bra", 1000.0, "url", 2);
        OrderItem item = new OrderItem(1L, 10, 0.0);
        CustomerInfo customer = new CustomerInfo("Ali", "Stockholm", "ali@example.com");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> orderService.createOrder(customer, List.of(item)));
    }
}