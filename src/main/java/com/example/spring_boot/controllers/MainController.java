package com.example.spring_boot.controllers;

import com.example.spring_boot.dao.ProductDAO;
import com.example.spring_boot.models.Product.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class MainController {
    private ProductDAO productDAO;

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @PostMapping("/products")
    public List<Product> saveProduct(@RequestBody Product product) {
        productDAO.save(product);
        return productDAO.findAll();
    }

    @GetMapping("/products")
    public List<Product> getProductsList() {
        return productDAO.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDAO.findById(id).get();
    }

    @PatchMapping("/products/{id}")
    public Product editProduct(@RequestBody Product product) {
        return productDAO.save(product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        productDAO.deleteById(id);
        return "User #" + id + " deleted";
    }
}

