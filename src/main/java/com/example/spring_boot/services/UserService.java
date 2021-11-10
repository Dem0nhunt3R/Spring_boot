package com.example.spring_boot.services;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.models.User;
import com.example.spring_boot.models.dto.UserRequestDTO.UserRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    public void save(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userDAO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findUserByUsername(username);
    }
}
