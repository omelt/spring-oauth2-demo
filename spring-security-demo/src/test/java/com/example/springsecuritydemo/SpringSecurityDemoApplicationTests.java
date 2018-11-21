package com.example.springsecuritydemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityDemoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(new BCryptPasswordEncoder().encode("123456").toString());
		System.out.println("$2a$10$VFOAP5ru9QlTAhqqLzBBs.7vDqgZFXssO9ZqdP9anPD2a1WLWMPYi");
	}

}
