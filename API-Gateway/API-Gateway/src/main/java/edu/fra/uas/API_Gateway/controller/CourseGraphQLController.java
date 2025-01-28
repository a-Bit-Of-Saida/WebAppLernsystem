package edu.fra.uas.API_Gateway.controller;

import org.springframework.http.HttpEntity;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses API", description = "REST-zu-GraphQL API für Kurse")
public class CourseGraphQLController {

    private final String COURSE_SERVICE_URL = "http://localhost:8082/graphql";

    // Weiterleitung der Anfrage an den Course-Service
    private ResponseEntity<?> forwardToCourseService(String query, Map<String, Object> variables) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> graphqlRequest = Map.of("query", query, "variables", variables);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(graphqlRequest, headers);

        System.out.println("GraphQL Request (an Course-Service): " + graphqlRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(COURSE_SERVICE_URL, entity, String.class);
        System.out.println("GraphQL Response (vom Course-Service): " + response.getBody());
        return response;
    }

    // REST-Endpunkt für alle Kurse abrufen
    @Operation(summary = "Hole alle Kurse", description = "Holt alle Kurse")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Kurse gefunden")})
    @GetMapping("/all")
    public ResponseEntity<?> getAllCourses() {
        String query = "query { allCourses { id name description instructor files { id name description } } }";
        Map<String, Object> variables = Map.of();
        return forwardToCourseService(query, variables);
    }

    // REST-Endpunkt für neuen Kurs hinzufügen
    @Operation(summary = "Füge einen neuen Kurs hinzu", description = "Fügt einen neuen Kurs hinzu")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Kurs hinzugefügt")})
    @PostMapping("/add")
    public ResponseEntity<?> addCourse(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String instructor) {
        String mutation = "mutation($name: String!, $description: String!, $instructor: String!) { addCourse(name: $name, description: $description, instructor: $instructor) { id name description instructor } }";
        Map<String, Object> variables = Map.of("name", name, "description", description, "instructor", instructor);
        return forwardToCourseService(mutation, variables);
    }
}
