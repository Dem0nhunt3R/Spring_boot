package com.example.spring_boot.dao;

import com.example.spring_boot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
