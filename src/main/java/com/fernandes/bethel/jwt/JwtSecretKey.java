package com.fernandes.bethel.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;


@Configuration//Properties("application.jwt")
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    @ConfigurationProperties("application.jwt")
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
