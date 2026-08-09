package com.shashank.storeapi.service;

import com.shashank.storeapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Sauce Labs Backpack", 29.99, "Fits 15-inch laptops"));
        products.add(new Product(2L, "Sauce Labs Bike Light", 9.99, "Water-resistant bike light"));
        products.add(new Product(3L, "Sauce Labs Bolt T-Shirt", 15.99, "100% cotton t-shirt"));
        products.add(new Product(4L, "Sauce Labs Fleece Jacket", 49.99, "Warm and windproof"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}