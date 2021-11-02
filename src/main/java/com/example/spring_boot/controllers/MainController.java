package com.example.spring_boot.controllers;

import com.example.spring_boot.dao.ProductDAO;
import com.example.spring_boot.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private ProductDAO productDAO;

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        productDAO.save(product);
        return productDAO.getById(product.getId());
    }

    @GetMapping("/products")
    public List<Product> getProducts () {
        return productDAO.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productDAO.findById(id).get();
    }

    @PatchMapping("/products")
    public Product updateProduct  (@RequestBody Product product) {
        return productDAO.save(product);
    }

    @DeleteMapping("/products/{id}")
    public List<Product> deleteProduct(@PathVariable int id) {
        productDAO.deleteById(id) ;
        return productDAO.findAll();
    }

}

