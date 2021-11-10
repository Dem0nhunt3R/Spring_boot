package com.example.spring_boot.services;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.models.User;
import com.example.spring_boot.models.dto.UserRequestDTO;
import com.example.spring_boot.models.dto.UserResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;

    public List<UserResponseDTO> findAll() {
        return userDAO.findAll().stream().map((user) -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setLogin(userRequestDTO.getLogin());
        user.setPassword(userRequestDTO.getPassword());
        userDAO.save(user);
    }

    public UserResponseDTO active(int id) {
        User user = userDAO.findById(id).get();
        user.setActivated(true);
        userDAO.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setLogin(user.getLogin());
        return userResponseDTO;
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .signWith(SignatureAlgorithm.HS512, "secret".getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public User findUserByLoginAndPassword(UserRequestDTO userRequestDTO) throws Exception {
        String login = userRequestDTO.getLogin();
        String password = userRequestDTO.getPassword();
        User user = userDAO.findUserByLoginAndPassword(login, password);
        if (user != null) {
            String token = generateToken(user);
            user.setToken(token);
            userDAO.save(user);
        } else throw new Exception();
        return user;
    }

    public User extractUserByToken(HttpServletRequest request) {
        return userDAO.findUserByToken(request.getHeader("Authorization"));
    }

    public ResponseEntity<UserResponseDTO> doResponse(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", user.getToken());
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setLogin(user.getLogin());
        return new ResponseEntity<UserResponseDTO>(userResponseDTO,httpHeaders, HttpStatus.OK);
    }
}
