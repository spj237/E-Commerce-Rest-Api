package com.example.Gescom.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String description;
    private  double price;
    private String pictureName;

    // Relation : plusieurs produits peuvent appartenir à une catégorie
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    // Relation Many-to-Many avec Orders
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();

    public Product(Long id, String name, String description, double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;

    }

    public Product() {
    }

}
