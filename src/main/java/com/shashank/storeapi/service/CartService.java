package com.shashank.storeapi.service;

import com.shashank.storeapi.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartService {

    private final List<CartItem> cart = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public CartItem addToCart(Long productId, int quantity) {
        CartItem item = new CartItem(idCounter.getAndIncrement(), productId, quantity);
        cart.add(item);
        return item;
    }

    public List<CartItem> getCart() {
        return cart;
    }
}