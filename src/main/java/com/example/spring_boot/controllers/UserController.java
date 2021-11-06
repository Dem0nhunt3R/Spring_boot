package com.example.spring_boot.controllers;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.models.User;
import com.example.spring_boot.services.MailService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserDAO userDAO;
    private MailService mailService;

    @SneakyThrows
    @PostMapping("/registration")
    public String register(@RequestBody User user){
        userDAO.save(user);
        mailService.send(user.getLogin(),user.getId());
        return "register";
    }
    
    @GetMapping("/activate/{id}")
    public String activate(@PathVariable int id){
        User user = userDAO.findById(id).get();
        user.setActivated(true);
        userDAO.save(user);
        return "Your account is activated";
    }
}



