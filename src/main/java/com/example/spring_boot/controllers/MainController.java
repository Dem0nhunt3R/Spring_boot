package com.example.spring_boot.controllers;


import com.example.spring_boot.models.Product;
import com.example.spring_boot.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private ProductService productService;
    
    @PostMapping("/product")
    public List<Product> saveProduct(@RequestBody Product product){
        return productService.saveProductWithCategory(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        System.out.println("get user");
        return productService.getProductById(id);
    }

    @DeleteMapping("/product/{id}")
    public List<Product> deleteProduct(@PathVariable int id){
        return productService.deleteProductById(id);
    }

}
