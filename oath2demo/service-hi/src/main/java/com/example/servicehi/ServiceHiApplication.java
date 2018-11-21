package com.example.servicehi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableDiscoveryClient
@EnableResourceServer
@SpringBootApplication
public class ServiceHiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHiApplication.class, args);
	}
}
// 进行注册
// curl -X POST -d "username=miya&password=123456" localhost:8762/user/registry
// 通过上面的用户的获取token
// curl service-hi:123456@localhost:5000/uaa/oauth/token  -d username=miya -d password=123456 -d grant_type=password
// 在headers中添加token  Bearer {token} 访问需要认证的目标
// curl -l -H  "Authorization:Bearer a81cc4de-c6ad-4ed9-83c5-7ed1ba0e46b7" localhost:8762/hi
