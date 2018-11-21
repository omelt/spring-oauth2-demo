package com.example.servicehi.service;


import com.example.servicehi.dao.UserDao;
import com.example.servicehi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail implements UserDetailsService,UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("service===>>>"+userRepository.findByUsername(username).getPassword());

        return userRepository.findByUsername(username);
    }

    @Override
    public User create(User user) {
        String hash=encoder.encode(user.getPassword());
        user.setPassword(hash);
        User u=userRepository.save(user);
        return u;
    }
}
