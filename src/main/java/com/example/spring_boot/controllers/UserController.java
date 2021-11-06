package com.example.spring_boot.controllers;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.models.dto.UserRequestDTO;
import com.example.spring_boot.models.dto.UserResponseDTO;
import com.example.spring_boot.models.User.User;
import com.example.spring_boot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserDAO userDAO;
    private UserService userService;

    @PostMapping("/registration")
    public void userRegistration(@RequestBody UserRequestDTO userRequestDTO){
        User user = new User();
        user.setLogin(userRequestDTO.getLogin());
        user.setPassword(userRequestDTO.getPassword());
        userDAO.save(user);
    }

    @GetMapping("/activation/{id}")
    public void userActivation(@PathVariable int id){
        User user = userDAO.findById(id).get();
        user.setActivated(true);
        userDAO.save(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDTO userRequestDTO) throws Exception {

        String login = userRequestDTO.getLogin();
        String password = userRequestDTO.getPassword();
        User user = userDAO.findUserByLoginAndPassword(login, password);

        if(user == null){
            throw new Exception();
        }
        String token = userService.tokenizer(user);
        user.setToken(token);
        userDAO.save(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token",token);
        return new ResponseEntity<>(new UserResponseDTO(user.getId(), user.getLogin()), httpHeaders, 200);
    }
    
    @PostMapping("/modify")
    public void modify(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
    }
}
