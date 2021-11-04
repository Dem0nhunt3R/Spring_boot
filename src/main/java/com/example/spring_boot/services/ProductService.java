package com.example.spring_boot.services;

import com.example.spring_boot.dao.CategoryDAO;
import com.example.spring_boot.dao.ProductDAO;
import com.example.spring_boot.models.Category;
import com.example.spring_boot.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public List<Product> saveProductWithCategory(Product product) {
        productDAO.save(product);
        List<Category> categories = product.getCategoriesList();
        for (Category category : categories) {
            category.setProduct(product);
            categoryDAO.save(category);
        }
        return productDAO.findAll();
    }


    public Product getProductById(int id) {
        return productDAO.findById(id).orElse(new Product("not exist",0,null,false));
    }

    public List<Product> deleteProductById(int id) {
        productDAO.deleteById(id);
        return productDAO.findAll();
    }
}
