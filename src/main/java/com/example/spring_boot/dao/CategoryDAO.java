package com.example.spring_boot.dao;

import com.example.spring_boot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
