package com.example.config;

import com.example.dto.TokenDTO;
import com.example.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigureInterceptor implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authorizationIntegration;

    @Bean
    @RequestScope
    TokenDTO tokenDataDTO() {
        return new TokenDTO();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(authorizationIntegration, tokenDataDTO()));
    }
}
