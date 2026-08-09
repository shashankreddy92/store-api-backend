package com.shashank.storeapi.service;

import com.shashank.storeapi.model.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderProcessor {

    @Async
    public void processOrderAsync(String orderId, Map<String, Order> orders) {
        try {
            Thread.sleep(5000);
            orders.get(orderId).setStatus("COMPLETED");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}