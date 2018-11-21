package com.example.servicehi.web;


import com.sun.istack.internal.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HiController {
    Logger logger = Logger.getLogger(HiController.class);

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(){
        return "hi :"+",i am from port: "+port;
    }

    @RequestMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String hello(){
        return "hello";
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication
    , Principal principal, Authentication authentication){
        logger.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        logger.info("principal.toString()"+principal.toString());
        logger.info("principal.toString()"+principal.getName());
        logger.info("authentication:"+authentication.getAuthorities().toString());
        return oAuth2Authentication;
    }
}



















