package com.example.spring_boot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {
    private String  title;
    private int price;
}
