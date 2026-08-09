package com.shashank.storeapi.controller;

import com.shashank.storeapi.model.CartItem;
import com.shashank.storeapi.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf(body.get("productId").toString());
        int quantity = Integer.parseInt(body.get("quantity").toString());
        CartItem item = cartService.addToCart(productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCart();
    }
}