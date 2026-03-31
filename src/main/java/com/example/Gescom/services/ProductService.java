package com.example.Gescom.services;

import com.example.Gescom.dtos.ProductDto;
import com.example.Gescom.dtos.Response;
import com.example.Gescom.entities.Category;
import com.example.Gescom.entities.Product;
import com.example.Gescom.repositories.CategoryRepository;
import com.example.Gescom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

//    private final ProductRepository productRepository;
//    private final CategoryRepository categoryRepository;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    @Value("${poster}")
    private String path;

    @Value("${baseUrl}")
    private String baseUrl;

//    public String create(String name, String description, double price){
//
//        Product product = new Product();
//        product.setName(name);
//        product.setPrice(price);
//        product.setDescription(description);
//
//        productRepository.save(product);
//
//        return "Operation reussie";
//
//    }

    public Response create(ProductDto product, MultipartFile file) throws IOException {
        try{Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec ID : " + product.getCategoryId()));

   String uploadFileName = fileService.uploadFile(path, file);

        Product newProduct = new Product();


        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setCategory(category);
        newProduct.setPictureName(uploadFileName);


        productRepository.save(newProduct);
        return new Response(200, true, "Produits récupérés avec succès", product, null);

    } catch (Exception e) {
        return new Response(500, false, "Erreur lors de la récupération des produits", null, e.getMessage());
    }
    }

//    public List<Product> getAll() {
//        return  productRepository.findAll();
//
//    }

    public Response getAll() {


        try{List<ProductDto> productDtoList= productRepository.findAll()
                .stream()
                .map(product -> {
                    String pictureUrl = baseUrl + "/file/" + product.getPictureName();
                    ProductDto dto = new ProductDto();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setPictureName(product.getPictureName());
                    dto.setPictureUrl(pictureUrl);
                    dto.setPrice(product.getPrice());
                    dto.setDescription(product.getDescription());
                    dto.setCategory(product.getCategory()); // objet complet
                    dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
                    return dto;
                })
                .collect(Collectors.toList());
        return new Response(200, true, "Produits récupérés avec succès", productDtoList, null);

    } catch (Exception e) {
        return new Response(500, false, "Erreur lors de la récupération des produits", null, e.getMessage());
    }
    }


    public Response getById(Long id) {
try{
        ProductDto productDto= productRepository.findById(id).map(product ->
                {String url=baseUrl+"/file/"+product.getPictureName();
                  return   new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getPictureName(),url,product.getCategory(),product.getCategory().getId());
                })
                .orElseThrow(() -> new RuntimeException("Produit non trouver avec Id : " + id));
        return new Response(200, true, "Produits récupérés avec succès", productDto, null);

    } catch (Exception e) {
        return new Response(500, false, "Erreur lors de la récupération des produits", null, e.getMessage());
    }
    }

//    public String update(Long id, String name, String description, double price){
//        Product product = getById(id);
//        product.setName(name);
//        product.setPrice(price);
//        product.setDescription(description);
//
//        productRepository.save(product);
//        return "produit modifier";
//    }

    public Response update(Long id, ProductDto productDto, MultipartFile file) throws IOException {
      try{  Product product =productRepository.findById(id).orElseThrow();

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec ID : " + productDto.getCategoryId()));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        if (file != null && !file.isEmpty()) {
           product.setPictureName(fileService.uploadFile(path,file));
        }

        productRepository.save(product);

        return new Response(200, true, "Produits récupérés avec succès", product, null);

    } catch (Exception e) {
        return new Response(500, false, "Erreur lors de la récupération des produits", null, e.getMessage());
    }
    }

    public String delete(Long id) {

        productRepository.deleteById(id);
        return "produit supprimer";


    }


    public Response getAllPaginated(int page, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(page, pageSize); // 10 produits par page

            Page<ProductDto> productPage = productRepository.findAll(pageable)
                    .map(product -> {
                        String pictureUrl = baseUrl + "/file/" + product.getPictureName();
                        ProductDto dto = new ProductDto();
                        dto.setId(product.getId());
                        dto.setName(product.getName());
                        dto.setPrice(product.getPrice());
                        dto.setDescription(product.getDescription());
                        dto.setCategory(product.getCategory());
                        dto.setPictureName(product.getPictureName());
                        dto.setPictureUrl(pictureUrl);
                        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
                        return dto;
                    });

            return new Response(200, true, "Produits récupérés avec succès", productPage, null);

        } catch (Exception e) {
            return new Response(500, false, "Erreur lors de la récupération des produits", null, e.getMessage());
        }
    }

}
