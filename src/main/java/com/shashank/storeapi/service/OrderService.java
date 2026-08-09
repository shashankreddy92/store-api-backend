package com.shashank.storeapi.service;

import com.shashank.storeapi.model.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private final OrderProcessor orderProcessor;

    public OrderService(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    public Order createOrder() {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, "PENDING");
        orders.put(orderId, order);
        orderProcessor.processOrderAsync(orderId, orders); // now a genuine cross-bean call
        return order;
    }

    public Order getOrderStatus(String orderId) {
        return orders.get(orderId);
    }
}