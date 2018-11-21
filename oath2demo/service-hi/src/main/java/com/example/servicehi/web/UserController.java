package com.example.servicehi.web;

import com.example.servicehi.pojo.User;
import com.example.servicehi.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceDetail userServiceDetail;

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public User createUser(@ModelAttribute User user){
        System.out.println(user);
        return userServiceDetail.create(user);
    }
}
