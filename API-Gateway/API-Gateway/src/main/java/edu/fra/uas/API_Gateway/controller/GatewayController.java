package edu.fra.uas.API_Gateway.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import edu.fra.uas.API_Gateway.exception.BadRequestException;
import edu.fra.uas.API_Gateway.exception.InternalServerErrorException;

@RestController
@RequestMapping
public class GatewayController {

    private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

    private final RestTemplate restTemplate = new RestTemplate();

    // Directly defined URLs for the services
    private final String coursesServiceUrl = "http://localhost:8080";
    private final String taskServiceUrl = "http://localhost:8081";
    private final String loginServiceUrl = "http://localhost:8082";

    // Forward the request to the appropriate service with `/graphql` endpoint
    @RequestMapping("/graphql")
    public ResponseEntity<Object> forwardToGraphqlService(ServerWebExchange exchange,
            @RequestBody String body,
            @RequestHeader Map<String, String> headers) {
        String targetUrl;
        try {
            targetUrl = determineTargetUrl(body);
        } catch (IllegalArgumentException e) {
            logger.error("Error determining target URL", e);
            throw new BadRequestException("Invalid GraphQL query: " + e.getMessage());
            
        }
        logger.info("Forwarding to URL: {}", targetUrl); // Debugging-Log
        return forwardRequest(targetUrl, headers, body, exchange.getRequest().getMethod().name());
    }

    // Forward the request to the Login service
    @RequestMapping("/auth/login")
    public ResponseEntity<Object> forwardToLoginService(ServerWebExchange exchange,
            @RequestBody(required = false) String body,
            @RequestHeader Map<String, String> headers) {
                
        // Extract query parameters from the request
        String query = exchange.getRequest().getURI().getQuery();
        String targetUrl = loginServiceUrl + exchange.getRequest().getPath().toString();
        if (query != null) {
            targetUrl += "?" + query;
        }
        logger.info("Forwarding to URL: {}", targetUrl); // Debugging-Log
        logger.debug("Body: {}", body); // Debugging-Log

        // Add the Content-Type header to the request
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        httpHeaders.set("Content-Type", "application/json");

        // Create HTTP entity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

        try {
            return restTemplate.exchange(targetUrl, HttpMethod.POST, requestEntity, Object.class);
        } catch (Exception e) {
            logger.error("Error forwarding request", e); // Debugging-Log
            throw new InternalServerErrorException("Error forwarding request to " + targetUrl, e);
        }
    }

    // Forward CRUD operations for users
    @RequestMapping("/auth/**")
    public ResponseEntity<Object> forwardToUserService(ServerWebExchange exchange,
            @RequestBody(required = false) String body,
            @RequestHeader Map<String, String> headers) {
        String targetUrl = loginServiceUrl + exchange.getRequest().getPath().toString();
        HttpMethod method = exchange.getRequest().getMethod();

        // Create HTTP headers
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::set);

        // Create HTTP entity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

        try {
            // Forward the request to the target URL
            return restTemplate.exchange(targetUrl, method, requestEntity, Object.class);
        } catch (Exception e) {
            logger.error("Error forwarding request to {}: {}", targetUrl, e.getMessage(), e); // Debugging-Log
            throw new InternalServerErrorException("Error forwarding request to " + targetUrl, e);
        }
    }

    private String determineTargetUrl(String body) {

        // Analyze the GraphQL query to determine the target service
        logger.debug("Received body: {}", body); // Debugging-Log
        if (body.contains("allCourses") || body.contains("courseById") || body.contains("courseByName")
                || body.contains("addCourse") || body.contains("updateCourse") || body.contains("deleteCourse")
                || body.contains("addFileToCourse") || body.contains("deleteFileFromCourse")) {
            logger.info("Routing to Course Service"); // Debugging-Log
            return coursesServiceUrl + "/graphql";
        } else if (body.contains("tasksByUserId") || body.contains("ToDoList") || body.contains("taskDueToday")
                || body.contains("addTask") || body.contains("deleteTask") || body.contains("updateTask")) {
            logger.info("Routing to Task Service"); // Debugging-Log
            return taskServiceUrl + "/graphql";
        } else {
            logger.error("Unknown query, returning error"); // Debugging-Log
            throw new IllegalArgumentException("Unknown query in request body");
        }
    }

    // Forward the request to the target service
    private ResponseEntity<Object> forwardRequest(String url, Map<String, String> headers, String body, String method) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

        try {
            return restTemplate.exchange(url, HttpMethod.valueOf(method), requestEntity, Object.class);
        } catch (Exception e) {
            logger.error("Error forwarding request to {}: {}", url, e.getMessage(), e); // Debugging-Log
            throw new InternalServerErrorException("Error forwarding request to " + url, e);
        }
    }
}
