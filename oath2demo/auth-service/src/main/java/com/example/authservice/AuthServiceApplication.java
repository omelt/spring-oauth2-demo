package com.example.authservice;

import com.example.authservice.service.UserServiceDetail;
import com.example.authservice.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;


//curl service-hi:123456@localhost:5000/uaa/oauth/token -d grant_type=password -d username=lctest -d password=123456
//curl localhost:5000/uaa/oauth/token -H "Authorization:Basic c2VydmljZS1oaToxMjM0NTY=" -d username=lctest -d password=123456 -d grant_type=password
//其中2VydmljZS1oaToxMjM0NTY=是由service-hi:123456经过base64加密而来的

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
public class AuthServiceApplication {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.addAllowedOrigin("*");
//		corsConfiguration.addAllowedHeader("*");
//		corsConfiguration.addAllowedMethod("*");
//		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//		return new CorsFilter(urlBasedCorsConfigurationSource);
//	}

	@Configuration
	@EnableAuthorizationServer
//    @CrossOrigin
	protected class OAth2AuthorzationConfig extends AuthorizationServerConfigurerAdapter{
		JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;


		@Autowired
		private UserServiceDetail userServiceDetail;

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("brower")
					.authorizedGrantTypes("refresh_token","password")
					.scopes("ui")
					.and()
					.withClient("service-hi")
					.secret("123456")
					.authorizedGrantTypes("client_credentials","refresh_token","password")
					.scopes("server");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints           //配置token的储存方式 用户管理 服务
					.tokenStore(tokenStore)
					.authenticationManager(authenticationManager)
					.userDetailsService(userServiceDetail);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security
					.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()");
		}
	}

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }
}
























