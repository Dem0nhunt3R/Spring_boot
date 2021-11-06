package ua.com.owu.june2021springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }



}