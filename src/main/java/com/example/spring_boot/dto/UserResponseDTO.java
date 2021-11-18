package com.example.spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private int id;
    private String  username;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String type;
}
