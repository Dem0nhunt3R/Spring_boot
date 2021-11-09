package com.example.spring_boot.controllers;

import com.example.spring_boot.models.User;
import com.example.spring_boot.models.dto.ProductDTO;
import com.example.spring_boot.models.dto.UserRequestDTO;
import com.example.spring_boot.models.dto.UserResponseDTO;
import com.example.spring_boot.services.ProductService;
import com.example.spring_boot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class UserController {
    private UserService userService;
    private ProductService productService;

    @PostMapping("/registration")
    public List<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
        return userService.findAll();
    }

    @GetMapping("/activation/{id}")
    public UserResponseDTO activateUser(@PathVariable int id) {
        return userService.active(id);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        return userService.doResponse(userService.findUserByLoginAndPassword(userRequestDTO));
    }

    @PostMapping("/addProduct")
    public String addUserProduct(HttpServletRequest request, @RequestBody ProductDTO productDTO) throws Exception {
        User user = userService.extractUserByToken(request);
        if (user != null) {
            productService.saveProduct(productDTO, user);
        } else throw new Exception();
        return "Your product:" + productDTO.getTitle() + "has been added with price:" + productDTO.getPrice();
    }
}
