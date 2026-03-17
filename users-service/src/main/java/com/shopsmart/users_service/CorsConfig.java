package com.shopsmart.users_service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite que el archivo HTML del navegador se comunique con este servidor
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST");
    }
}