package com.example.uaaservice.util;

import org.springframework.security.crypto.password.PasswordEncoder;


public class MyPasswordEncoder implements PasswordEncoder{

    public String encode(CharSequence rawPassword) {
        System.out.println("before encode ===>>>  "+rawPassword.toString());
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("before matches ===>>>  "+rawPassword.toString()+" ||| "+encodedPassword);   //初步看起来 这里的俩个密码是经过 security 解密过的了
        return rawPassword.toString().equals(encodedPassword);
    }


}
