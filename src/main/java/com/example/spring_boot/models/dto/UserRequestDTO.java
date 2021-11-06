package com.example.spring_boot.models.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String  login;
    private String  password;
}
