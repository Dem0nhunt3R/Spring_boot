package com.example.spring_boot.dao;

import com.example.spring_boot.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {

    User findUserByLoginAndPassword(String login, String password);

}
