package com.example.configclient.service.hystrix;

import com.example.configclient.pojo.JWT;
import com.example.configclient.service.AuthServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AuthServiceHystrix implements AuthServiceClient{
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        System.out.println("========error when get Token=======");
        return null;
    }
}
