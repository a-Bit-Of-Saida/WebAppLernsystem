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
                registry.addMapping("/**")  // Gilt für alle Endpunkte
                        .allowedOrigins("http://localhost:3000")  // Erlaubt Anfragen vom React-Frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Erlaubte HTTP-Methoden
                        .allowedHeaders("*")  // Alle Header erlauben
                        .allowCredentials(true);  // Falls Cookies oder Auth-Header genutzt werden
            }
        };
    }
}