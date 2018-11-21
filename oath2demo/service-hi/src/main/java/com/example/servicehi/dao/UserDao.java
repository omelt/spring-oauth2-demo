package com.example.servicehi.dao;


import com.example.servicehi.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
    User findByUsername(String username);



}
