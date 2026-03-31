package com.example.Gescom.dtos;

import com.example.Gescom.entities.Category;
import lombok.Data;

@Data
public class ProductDto {

    private Long Id;
    private String name;
    private String description;
    private double price;
    private String pictureName;
    private String pictureUrl;

    // Quand on fait GET → on aura toute la catégorie
    private Category category;

    // Quand on fait POST/PUT → on enverra juste l'id
    private Long categoryId;


    public ProductDto() {
    }


    public ProductDto(Long id, String name, String description, double price, String pictureName, String pictureUrl, Category category, Long categoryId) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureName = pictureName;
        this.pictureUrl = pictureUrl;
        this.category = category;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
