package com.example.springsecuritydemo.config;


import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //spring security 5 添加密码加密
        //如果from database 要auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
        //详细再说

        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
//
//        manager.createUser(User.withUsername("lctest")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("USER").build());
//
//        manager.createUser(User.withUsername("admin")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("ADMIN","USER").build());
//
//        return manager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/blog/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").failureForwardUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/401")
                .and()
                .logout().logoutSuccessUrl("/");

    }
}
