package com.example.userservice.dao;


import com.example.userservice.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
    User findByUsername(String username);



}
