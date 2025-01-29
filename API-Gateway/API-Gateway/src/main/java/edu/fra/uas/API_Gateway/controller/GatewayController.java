package edu.fra.uas.API_Gateway.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

@RestController
@RequestMapping
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();

    // Direkt definierte Service-URLs
    private final String coursesServiceUrl = "http://localhost:8080";
    private final String taskServiceUrl = "http://localhost:8081";
    private final String loginServiceUrl = "http://localhost:8082";

    // Weiterleitung für GraphQL mit `/graphql`
    @RequestMapping("/graphql")
    public ResponseEntity<Object> forwardToGraphqlService(ServerWebExchange exchange,
            @RequestBody String body,
            @RequestHeader Map<String, String> headers) {
        String targetUrl;
        try {
            targetUrl = determineTargetUrl(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
        System.out.println("Forwarding to URL: " + targetUrl); // Debugging-Log
        return forwardRequest(targetUrl, headers, body, exchange.getRequest().getMethod().name());
    }

    @RequestMapping("/auth/login")
    public ResponseEntity<Object> forwardToLoginService(ServerWebExchange exchange,
            @RequestBody(required = false) String body,
            @RequestHeader Map<String, String> headers) {
        // Query-String aus der Anfrage extrahieren
        String query = exchange.getRequest().getURI().getQuery();
        String targetUrl = loginServiceUrl + exchange.getRequest().getPath().toString();
        if (query != null) {
            targetUrl += "?" + query;
        }
        System.out.println("Forwarding to URL: " + targetUrl); // Debugging-Log
        System.out.println("Body: " + body); // Debugging-Log

        // Füge Content-Type-Header hinzu
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        httpHeaders.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

        try {
            return restTemplate.exchange(targetUrl, HttpMethod.POST, requestEntity, Object.class);
        } catch (Exception e) {
            System.err.println("Error forwarding request: " + e.getMessage()); // Debugging-Log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    private String determineTargetUrl(String body) {
        // Analyze the GraphQL query to determine the target service
        System.out.println("Received body: " + body); // Debugging-Log
        if (body.contains("allCourses") || body.contains("courseById") || body.contains("courseByName")
                || body.contains("addCourse") || body.contains("updateCourse") || body.contains("deleteCourse")
                || body.contains("addFileToCourse") || body.contains("deleteFileFromCourse")) {
            System.out.println("Routing to Course Service"); // Debugging-Log
            return coursesServiceUrl + "/graphql";
        } else if (body.contains("Tasks") || body.contains("ToDoList") || body.contains("taskDueToday")
                || body.contains("addTask") || body.contains("deleteTask") || body.contains("updateTask")) {
            System.out.println("Routing to Task Service"); // Debugging-Log
            return taskServiceUrl + "/graphql";
        } else {
            System.out.println("Unknown query, returning error"); // Debugging-Log
            throw new IllegalArgumentException("Unknown query in request body");
        }
    }

    private ResponseEntity<Object> forwardRequest(String url, Map<String, String> headers, String body, String method) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

        try {
            return restTemplate.exchange(url, HttpMethod.valueOf(method), requestEntity, Object.class);
        } catch (Exception e) {
            System.err.println("Error forwarding request: " + e.getMessage()); // Debugging-Log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
