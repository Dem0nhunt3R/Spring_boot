package com.example.spring_boot.dao;

import com.example.spring_boot.models.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDAO extends JpaRepository<AuthToken,Integer> {
    AuthToken findByToken(String token);
}
