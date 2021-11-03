package com.example.spring_boot.models.Product;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int price;
    private String type;
    private boolean isPresent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categoriesList;

    public Product(String title, int price, String type, boolean isPresent) {
        this.title = title;
        this.price = price;
        this.type = type;
        this.isPresent = isPresent;
    }
}
