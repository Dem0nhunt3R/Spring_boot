package com.example.spring_boot.controllers;

import com.example.spring_boot.models.dto.UserRequestDTO.UserRequestDTO;
import com.example.spring_boot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/registration")
    public void saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
    }

    @GetMapping("/get")
    public List<String> getData() {
        return Arrays.asList("hello", " okten");
    }
}
