package com.example.spring_boot.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
public class MainController {

    @GetMapping("/")
    public void hello(){
        System.out.println("hello");
    }

}
