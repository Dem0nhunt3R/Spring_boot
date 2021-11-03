package com.example.spring_boot.dao;

import com.example.spring_boot.models.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Integer> {
}
