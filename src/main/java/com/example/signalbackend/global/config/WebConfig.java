package com.example.signalbackend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST", "PUT", "PATCH", "GET", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins(
                        "https://signal.p-e.kr/",
                        "http://localhost:3000",
                        "http://localhost:3001"
                );
    }
}
