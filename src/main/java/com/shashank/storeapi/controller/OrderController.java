package com.shashank.storeapi.controller;

import com.shashank.storeapi.model.Order;
import com.shashank.storeapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder() {
        Order order = orderService.createOrder();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(order); // 202
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderStatus(@PathVariable String id) {
        Order order = orderService.getOrderStatus(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }
}