package com.example.userservice.service;

import com.example.userservice.pojo.JWT;
import com.example.userservice.service.hystrix.AuthServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "uaa-service",fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {

    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam(value = "grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password")String password);
}
