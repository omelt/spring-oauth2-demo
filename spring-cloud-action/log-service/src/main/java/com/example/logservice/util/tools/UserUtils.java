package com.example.logservice.util.tools;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class UserUtils {

    /**
     * 请求当前用户
     * @return
     */
    public static String getCurrentPrinciple(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 请求权限信息
     * @return
     */
    public static List<SimpleGrantedAuthority> getCurrentAuthorities(){
        return (List<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
