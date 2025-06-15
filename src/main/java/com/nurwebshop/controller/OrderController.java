package com.nurwebshop.controller;

import com.nurwebshop.model.CustomerInfo;
import com.nurwebshop.model.Order;
import com.nurwebshop.model.OrderItem;
import com.nurwebshop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders - Skapa en ny order
    @PostMapping
    public Map<String, Object> createOrder(@RequestBody OrderRequest orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest.getCustomerInfo(), orderRequest.getItems());

        return Map.of(
                "message", "Order mottagen!",
                "orderId", createdOrder.getId(),
                "totalAmount", createdOrder.getTotalAmount(),
                "orderDate", createdOrder.getOrderDate()
        );
    }

    // GET /api/orders - Hämta alla ordrar
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // DTO för att ta emot orderns JSON-data
    public static class OrderRequest {
        private CustomerInfo customerInfo;
        private List<OrderItem> items;

        public CustomerInfo getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(CustomerInfo customerInfo) {
            this.customerInfo = customerInfo;
        }

        public List<OrderItem> getItems() {
            return items;
        }

        public void setItems(List<OrderItem> items) {
            this.items = items;
        }
    }
}