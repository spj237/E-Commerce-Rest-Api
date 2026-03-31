package com.example.Gescom.dtos;

import com.example.Gescom.entities.Product;
import com.example.Gescom.entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrderDto {
    private Long id;
    private double total;
    private OrderStatus status;
    private List<Long> productIds;
    private Set<Product> products; // <-- remplacer productIds
    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}