package edu.fra.uas.API_Gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    /**
     * Bean definition for CorsWebFilter to handle CORS (Cross-Origin Resource Sharing) configuration.
     * This filter allows requests from specified origins and methods.
     *
     * @return CorsWebFilter configured with the specified CORS settings.
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Allow requests from the specified origin (React frontend running on localhost:3000)
        corsConfiguration.addAllowedOrigin("http://localhost:3000");

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        corsConfiguration.addAllowedMethod("*");

        // Allow all headers
        corsConfiguration.addAllowedHeader("*");

        // Allow credentials (cookies, authorization headers, etc.)
        corsConfiguration.setAllowCredentials(true);

        // Register the CORS configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        // Return the configured CorsWebFilter
        return new CorsWebFilter(source);
    }
}