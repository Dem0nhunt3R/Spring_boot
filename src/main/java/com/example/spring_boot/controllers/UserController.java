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

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ProductService productService;

    @PostMapping("/registration")
    public String registration(@RequestBody UserRequestDTO userRequestDTO){
        userService.saveUser(userRequestDTO);
        return "Registration complete";
    }

    @GetMapping("/activation/{id}")
    public String activation(@PathVariable int id){
        userService.activateUser(id);
        return "Your account has been activated";
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        User user = userService.findUserByLoginAndPassword(userRequestDTO);
        return userService.doResponse(user);
    }
    
    @PostMapping("/addProduct")
    public String addProduct(HttpServletRequest request,@RequestBody ProductDTO productDTO) throws Exception {
        User user = userService.extractTokenAndFindUser(request);
        if(user != null){
            productService.saveProduct(productDTO,user);
        }else{
            throw new Exception();
        }

        return "You added "+productDTO.getTitle()+" with "+productDTO.getPrice()+" price";
    }
}
