package com.example.spring_boot.models.Product;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  title;

    public Category(String title) {
        this.title = title;
    }
}
