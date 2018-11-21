package com.example.authservice;

import com.example.authservice.dao.UserDao;
import com.example.authservice.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceApplicationTests {

	@Autowired
	private UserDao userRepository;

	@Test
	public void contextLoads() {
		User user=userRepository.findByUsername("lctest");


		String tar=user.getPassword();
		System.out.println(tar);

//		System.out.println(user.getPassword());



		System.out.println(new BCryptPasswordEncoder().matches("123456",tar));
	}

	@Test
    public void printfTest(){
	   String tar= new BASE64Encoder().encode("service-hi:123456".getBytes());
	   System.out.println(tar);
    }

}
