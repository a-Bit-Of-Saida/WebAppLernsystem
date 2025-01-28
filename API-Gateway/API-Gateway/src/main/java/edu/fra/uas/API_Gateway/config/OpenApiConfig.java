package edu.fra.uas.API_Gateway.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

// Gateway konfigurieren
// REST-Anfragen an GraphQL-Service weiterleiten
@Configuration
@OpenAPIDefinition(
    info = @Info(title = "API Gateway", version = "1.0", description = "REST-zu-GraphQL API Gateway"),
    servers = @Server(url = "http://localhost:8081", description = "Lokal")
)
public class OpenApiConfig {
}
