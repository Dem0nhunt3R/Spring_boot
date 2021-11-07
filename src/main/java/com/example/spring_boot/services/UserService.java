package com.example.spring_boot.services;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.models.User;
import com.example.spring_boot.models.dto.UserRequestDTO;
import com.example.spring_boot.models.dto.UserResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;

    public void saveUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setLogin(userRequestDTO.getLogin());
        user.setPassword(userRequestDTO.getPassword());
        userDAO.save(user);
    }

    public void activateUser(int id){
        User user = userDAO.findById(id).get();
        user.setActivated(true);
        userDAO.save(user);
    }

    public User findUserByLoginAndPassword(UserRequestDTO userRequestDTO) throws Exception {
        String login = userRequestDTO.getLogin();
        String password = userRequestDTO.getPassword();
        User user = userDAO.findUserByLoginAndPassword(login,password);
        if(user==null){
            throw new Exception();
        }
        String token = this.createToken(user);
        user.setToken(token);
        userDAO.save(user);
        return user;
    }

    public String createToken(User user){
        return Jwts.builder()
                        .setSubject(user.getLogin())
                        .signWith(SignatureAlgorithm.HS512, "secret".getBytes(StandardCharsets.UTF_8))
                        .compact();
    }

    public User extractTokenAndFindUser(HttpServletRequest request){
        return userDAO.findUserByToken(request.getHeader("Authorization"));
    }

    public ResponseEntity<UserResponseDTO> doResponse(User user){
        //create new obj from HttpHeaders class
        HttpHeaders httpHeaders = new HttpHeaders();
        //add token
        httpHeaders.add("token",user.getToken());
        //create new UserResponseDTO obj
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        //set id and login
        userResponseDTO.setId(user.getId());
        userResponseDTO.setLogin(user.getLogin());
        //return new ResponseEntity obj with userResponse DTO obj, httpHeaders and status
        return new ResponseEntity<UserResponseDTO>(userResponseDTO,httpHeaders,200);
    }

}
