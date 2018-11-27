package com.example.configclient.web;


import com.example.configclient.pojo.User;
import com.example.configclient.pojo.UserLoginDTO;
import com.example.configclient.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceDetail userServiceDetail;

    @PostMapping("/register")
    public User postUser(@ModelAttribute User user){
        return userServiceDetail.addUser(user);
    }

    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username")String username, @RequestParam("password")String password){
        return userServiceDetail.login(username,password);
    }

    @RequestMapping(value = "/foo",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String foo(){
        return "i'm foo,"+ UUID.randomUUID().toString();
    }
}





















