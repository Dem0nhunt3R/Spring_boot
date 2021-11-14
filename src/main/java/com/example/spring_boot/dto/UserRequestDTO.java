package com.example.spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequestDTO {
    private String  login;
    private String  password;
}
