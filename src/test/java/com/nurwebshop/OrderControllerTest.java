package com.nurwebshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurwebshop.model.CustomerInfo;
import com.nurwebshop.model.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.nurwebshop.controller.OrderController;
import com.nurwebshop.model.Order;
import com.nurwebshop.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateOrder_success() throws Exception {
        CustomerInfo customerInfo = new CustomerInfo("Ali", "Stockholm", "ali@example.com");
        List<OrderItem> items = List.of(
                new OrderItem(1L, 2, 1000.0),
                new OrderItem(3L, 1, 500.0)
        );

        Order mockOrder = new Order();
        mockOrder.setId(UUID.randomUUID().toString());
        mockOrder.setCustomerInfo(customerInfo);
        mockOrder.setItems(items);
        mockOrder.setTotalAmount(2500.0);
        mockOrder.setOrderDate(LocalDateTime.now());

        when(orderService.createOrder(any(), any())).thenReturn(mockOrder);

        String requestBody = objectMapper.writeValueAsString(
                new OrderController.OrderRequest() {{
                    setCustomerInfo(customerInfo);
                    setItems(items);
                }}
        );

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Order mottagen!"))
                .andExpect(jsonPath("$.orderId").exists())
                .andExpect(jsonPath("$.totalAmount").value(2500.0))
                .andExpect(jsonPath("$.orderDate").exists());
    }
}