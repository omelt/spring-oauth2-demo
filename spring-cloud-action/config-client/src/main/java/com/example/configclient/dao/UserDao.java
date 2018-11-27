package com.example.configclient.dao;



import com.example.configclient.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
    User findByUsername(String username);



}
