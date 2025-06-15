package com.nurwebshop.service;

import com.nurwebshop.model.*;
import com.nurwebshop.repository.OrderRepository;
import com.nurwebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Skapa en ny order
    public Order createOrder(CustomerInfo customerInfo, List<OrderItem> items) {
        double totalAmount = 0.0;

        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produkt med ID " + item.getProductId() + " hittades inte"));

            if (item.getQuantity() <= 0 || item.getQuantity() > product.getStock()) {
                throw new RuntimeException("Ogiltig kvantitet för produkt: " + product.getName());
            }

            item.setPriceAtPurchase(product.getPrice());
            totalAmount += product.getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerInfo(customerInfo);
        order.setItems(items);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
        return order;
    }

    // Hämta alla ordrar
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
