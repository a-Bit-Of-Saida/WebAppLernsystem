package edu.fra.uas.Login_Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // for all endpoints
                        .allowedOrigins("http://localhost:3000")  // allows requests from React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // allowed HTTP methods
                        .allowedHeaders("*")  // allow all headers
                        .allowCredentials(true);  //if you want to send credentials like cookies
            }
        };
    }
}