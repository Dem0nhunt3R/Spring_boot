package com.example.spring_boot.models.User;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String  login;
    private String  password;
    private boolean isActivated = false;
    private String  token;

}
