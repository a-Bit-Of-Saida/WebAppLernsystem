package edu.fra.uas.API_Gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Weiterleitung für CourseService GraphQL
                .route("task_service_route", r -> r.path("/api/tasks/**")
                .filters(f -> f.rewritePath("/api/task/(?<segment>.*)", "/graphql"))
                .uri("http://localhost:8081")) 

                // Weiterleitung für CourseService GraphiQL
                .route("task_graphiql_route", r -> r.path("/task-graphiql")
                .uri("http://localhost:8081/graphiql")) 
                .build();
    }
}

