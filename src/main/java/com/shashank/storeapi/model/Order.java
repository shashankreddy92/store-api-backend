package com.shashank.storeapi.model;

public class Order {
    private String id;
    private String status; // PENDING, COMPLETED

    public Order(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}