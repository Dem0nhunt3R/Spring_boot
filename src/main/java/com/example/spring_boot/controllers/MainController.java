package com.example.spring_boot.controllers;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.dto.UserRequestDTO;
import com.example.spring_boot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(){
        System.out.println("lol");
        return "home";
    }

    @PostMapping("/registration")
    public void save(@RequestBody UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userDAO.save(user);
    }

}
