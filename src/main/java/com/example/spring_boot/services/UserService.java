package com.example.spring_boot.services;

import com.example.spring_boot.models.User.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String tokenizer(User user){
        return "token"+user.getId();
    }
}
