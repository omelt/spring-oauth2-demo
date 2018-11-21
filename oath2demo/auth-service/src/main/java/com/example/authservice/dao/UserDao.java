package com.example.authservice.dao;

import com.example.authservice.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
    User findByUsername(String username);



}
