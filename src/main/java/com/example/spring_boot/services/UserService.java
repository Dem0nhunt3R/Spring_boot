package com.example.spring_boot.services;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService{

    private UserDAO userDAO;

    public List<UserResponseDTO> findAll(){
        return userDAO.findAll().stream().map((user)->{
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            dto.setType(user.getType());
            return dto;
        }).collect(Collectors.toList());
    }

}
