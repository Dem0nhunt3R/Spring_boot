package com.example.spring_boot.controllers;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.dto.UserRequestDTO;
import com.example.spring_boot.dto.UserResponseDTO;
import com.example.spring_boot.models.AuthToken;
import com.example.spring_boot.models.User;
import com.example.spring_boot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public List<UserResponseDTO> home() {
        return userService.findAll();
    }

    @PostMapping("/registration")
    public void save(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setType(userRequestDTO.getType());
        userDAO.save(user);
    }
}
