package com.example.configclient.service;



import com.example.configclient.dao.UserDao;
import com.example.configclient.pojo.JWT;
import com.example.configclient.pojo.User;
import com.example.configclient.pojo.UserLoginDTO;
import com.example.configclient.util.exception.UserLoginException;
import com.example.configclient.util.tools.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("service===>>>"+username+" ||| "+userRepository.findByUsername(username).getPassword());

        return userRepository.findByUsername(username);
    }

    public User addUser(User user){
        String pwd= BPwdEncoderUtil.BCryptPassword(user.getPassword());
        user.setPassword(pwd);
        return userRepository.save(user);
    }

    @Autowired
    AuthServiceClient client;

    public UserLoginDTO login(String username, String password){
        User user=userRepository.findByUsername(username);
        if(null==user){
            throw new UserLoginException("error username");
        }
        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("error password");
        }
        // base64 加密 user-service:123456
        JWT jwt =client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        if (null==jwt){
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;
    }

}



















