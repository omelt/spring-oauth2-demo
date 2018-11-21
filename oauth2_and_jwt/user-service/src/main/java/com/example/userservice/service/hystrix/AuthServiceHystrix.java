package com.example.userservice.service.hystrix;

import com.example.userservice.pojo.JWT;
import com.example.userservice.service.AuthServiceClient;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient{
    @Override   //先这样把
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
