package com.example.spring_boot.services;

import com.example.spring_boot.dao.ProductDAO;
import com.example.spring_boot.models.Product;
import com.example.spring_boot.models.User;
import com.example.spring_boot.models.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductDAO productDAO;

    public void saveProduct(ProductDTO productDTO, User user){
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setUser(user);
        productDAO.save(product);
    }
}
