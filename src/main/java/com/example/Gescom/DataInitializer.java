package com.example.Gescom;

import com.example.Gescom.entities.Category;
import com.example.Gescom.entities.Product;
import com.example.Gescom.repositories.CategoryRepository;
import com.example.Gescom.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            if (categoryRepository.count() == 0 && productRepository.count() == 0) {

                // Création des catégories
                Category cat1 = new Category();
                cat1.setName("Électronique");
                categoryRepository.save(cat1);

                Category cat2 = new Category();
                cat2.setName("Vêtements");
                categoryRepository.save(cat2);

                Category cat3 = new Category();
                cat3.setName("Alimentation");
                categoryRepository.save(cat3);

                // Produits pour catégorie 1
                productRepository.save(new Product(null, "Smartphone", "Téléphone Android", 350.0, cat1));
                productRepository.save(new Product(null, "Ordinateur portable", "Laptop 15 pouces", 750.0, cat1));
                productRepository.save(new Product(null, "Casque Bluetooth", "Casque sans fil", 60.0, cat1));

                // Produits pour catégorie 2
                productRepository.save(new Product(null, "T-shirt", "T-shirt en coton", 15.0, cat2));
                productRepository.save(new Product(null, "Pantalon", "Jean bleu", 40.0, cat2));
                productRepository.save(new Product(null, "Veste", "Veste en cuir", 120.0, cat2));

                // Produits pour catégorie 3
                productRepository.save(new Product(null, "Riz", "Riz parfumé 5kg", 8.0, cat3));
                productRepository.save(new Product(null, "Huile", "Huile végétale 1L", 3.5, cat3));
                productRepository.save(new Product(null, "Café", "Café moulu 500g", 5.0, cat3));
                productRepository.save(new Product(null, "Smartphone", "Téléphone Android", 350.0, cat1));
                productRepository.save(new Product(null, "Ordinateur portable", "Laptop 15 pouces", 750.0, cat1));
                productRepository.save(new Product(null, "Casque Bluetooth", "Casque sans fil", 60.0, cat1));

                // Produits pour catégorie 2
                productRepository.save(new Product(null, "T-shirt", "T-shirt en coton", 15.0, cat2));
                productRepository.save(new Product(null, "Pantalon", "Jean bleu", 40.0, cat2));
                productRepository.save(new Product(null, "Veste", "Veste en cuir", 120.0, cat2));

                // Produits pour catégorie 3
                productRepository.save(new Product(null, "Riz", "Riz parfumé 5kg", 8.0, cat3));
                productRepository.save(new Product(null, "Huile", "Huile végétale 1L", 3.5, cat3));
                productRepository.save(new Product(null, "Café", "Café moulu 500g", 5.0, cat3));

                productRepository.save(new Product(null, "Smartphone", "Téléphone Android", 350.0, cat1));
                productRepository.save(new Product(null, "Ordinateur portable", "Laptop 15 pouces", 750.0, cat1));
                productRepository.save(new Product(null, "Casque Bluetooth", "Casque sans fil", 60.0, cat1));

                // Produits pour catégorie 2
                productRepository.save(new Product(null, "T-shirt", "T-shirt en coton", 15.0, cat2));
                productRepository.save(new Product(null, "Pantalon", "Jean bleu", 40.0, cat2));
                productRepository.save(new Product(null, "Veste", "Veste en cuir", 120.0, cat2));

                // Produits pour catégorie 3
                productRepository.save(new Product(null, "Riz", "Riz parfumé 5kg", 8.0, cat3));
                productRepository.save(new Product(null, "Huile", "Huile végétale 1L", 3.5, cat3));
                productRepository.save(new Product(null, "Café", "Café moulu 500g", 5.0, cat3));

                productRepository.save(new Product(null, "Smartphone", "Téléphone Android", 350.0, cat1));
                productRepository.save(new Product(null, "Ordinateur portable", "Laptop 15 pouces", 750.0, cat1));
                productRepository.save(new Product(null, "Casque Bluetooth", "Casque sans fil", 60.0, cat1));

                // Produits pour catégorie 2
                productRepository.save(new Product(null, "T-shirt", "T-shirt en coton", 15.0, cat2));
                productRepository.save(new Product(null, "Pantalon", "Jean bleu", 40.0, cat2));
                productRepository.save(new Product(null, "Veste", "Veste en cuir", 120.0, cat2));

                // Produits pour catégorie 3
                productRepository.save(new Product(null, "Riz", "Riz parfumé 5kg", 8.0, cat3));
                productRepository.save(new Product(null, "Huile", "Huile végétale 1L", 3.5, cat3));
                productRepository.save(new Product(null, "Café", "Café moulu 500g", 5.0, cat3));

                System.out.println("✅ Données initiales insérées avec succès !");
            } else {
                System.out.println("ℹ️ Données déjà présentes, initialisation ignorée.");
            }
        };
    }
}
