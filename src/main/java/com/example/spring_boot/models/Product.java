package com.example.spring_boot.models;


import javax.persistence.*;

import lombok.*;

import java.util.List;
import java.util.function.Supplier;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String  title;
    private int price;
    private String type;
    private boolean isPresent;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "product",
            orphanRemoval = true
    )
    private List<Category> categoriesList;

    public Product(String title, int price, String type, boolean isPresent) {
        this.title = title;
        this.price = price;
        this.type = type;
        this.isPresent = isPresent;
    }
}

