package com.shopsmart.inventory_service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// esto es para que el html pueda hacer fetch sin que el navegador bloquee
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST");
    }
}
