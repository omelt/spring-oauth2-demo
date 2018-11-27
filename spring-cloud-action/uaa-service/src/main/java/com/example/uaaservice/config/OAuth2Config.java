package com.example.uaaservice.config;

import com.example.uaaservice.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;


@Configuration
@EnableAuthorizationServer  //开启功能
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()   //在内存中建立客户端信息
                .withClient("user-service")
                .secret("123456")
                .scopes("service")
                .authorizedGrantTypes("refresh_token","password")
                .accessTokenValiditySeconds(3600);  //设置过期时间
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {  //配置
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager);
    }


    @Bean
    public TokenStore tokenStore(){  //JwtAccessTokenConverter用于token的转换
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer(){  //   使用RSA对JWT 加密
        KeyStoreKeyFactory keyStoreKeyFactory=new KeyStoreKeyFactory(new ClassPathResource("lc-jwt.jks"),"lctest".toCharArray());
        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("lc-jwt"));
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }
}


// 需要 keytool 生成 jks文件
//keytool -genkeypair -alias lc-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jwt,L=zurich,S=zurich,C=CH" -keypass lctest -keystore lc-jwt.jks -storepass lctest
// 获取公钥
//keytool -list -rfc --keystore lc-jwt.jks | openssl x509 -inform pem -pubkey

//为了防止编译乱码 需要在pom中配置
















