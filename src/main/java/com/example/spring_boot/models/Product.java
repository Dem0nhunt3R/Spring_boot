package com.example.spring_boot.models;

import com.example.spring_boot.dao.ProductDAO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  title;
    private int price;
    private String  type;
    private boolean isPresent;

    public Product(String title, int price, String type, boolean isPresent) {
        this.title = title;
        this.price = price;
        this.type = type;
        this.isPresent = isPresent;
    }
}
