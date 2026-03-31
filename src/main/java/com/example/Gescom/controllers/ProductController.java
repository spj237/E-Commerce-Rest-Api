package com.example.Gescom.controllers;

import com.example.Gescom.dtos.ProductDto;
import com.example.Gescom.dtos.Response;
import com.example.Gescom.entities.Product;
import com.example.Gescom.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping("/create")
//    public String createProduct(@RequestParam String name, @RequestParam String description, @RequestParam double price){
//        return productService.create(name, description, price);
//    }

    @PostMapping("/create")
    public Response createProduct(@RequestPart String productDtoString, @RequestPart MultipartFile file) throws IOException {

        ProductDto productDto = convertToProductDto(productDtoString);
        return productService.create(productDto, file);
    }

    @GetMapping("/all")
    public Response getAllProducts() {

        return productService.getAll();
    }

    @GetMapping("detail/{id}")
    public Response getProductById(@PathVariable Long id) {
        return productService.getById(id);

    }

//    @PutMapping("update/{id}")
//    public String updateProduct(@PathVariable Long id , String name, String description, double price){
//        return productService.update(id, name,description, price);
//    }

    @PutMapping("update/{id}")
    public Response updateProduct(@RequestPart String productDtoString, @RequestPart MultipartFile file, @PathVariable Long id) throws IOException {
        ProductDto product = convertToProductDto(productDtoString);
        return productService.update(id, product,file);
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        return productService.delete(id);

    }

    @GetMapping("/paginated")
    public Response getProductsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam int pageSize) {
        return productService.getAllPaginated(page, pageSize);
    }


    private ProductDto convertToProductDto(String productDtoString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(productDtoString, ProductDto.class);
    }


}
