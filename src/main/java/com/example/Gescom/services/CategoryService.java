package com.example.Gescom.services;

import com.example.Gescom.dtos.CategoryDto;
import com.example.Gescom.entities.Category;
import com.example.Gescom.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public String create(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return "Catégorie créée avec succès";
    }

    // READ ALL
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(cat -> new CategoryDto(cat.getId(), cat.getName()))
                .collect(Collectors.toList());
    }

    // READ BY ID
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'id : " + id));
    }

    // UPDATE
    public String update(Long id, CategoryDto categoryDto) {
        Category category = getById(id);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return "Catégorie mise à jour avec succès";
    }

    // DELETE
    public String delete(Long id) {
        Category category = getById(id);
        categoryRepository.delete(category);
        return "Catégorie supprimée avec succès";
    }
}
