package edu.fra.uas.API_Gateway.controller;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks API", description = "REST-zu-GraphQL API für Tasks")
public class TaskGraphQLController {

    private final String TASK_SERVICE_URL = "http://localhost:8081/graphql";

    private ResponseEntity<?> forwardToTaskService(String query, Map<String, Object> variables) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> graphqlRequest = Map.of("query", query, "variables", variables);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(graphqlRequest, headers);

        System.out.println("GraphQL Request (an Task-Service): " + graphqlRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(TASK_SERVICE_URL, entity, String.class);
        System.out.println("GraphQL Response (vom Task-Service): " + response.getBody());
        return response;
    }

    @Operation(summary = "Hole alle Tasks", description = "Holt alle Tasks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tasks gefunden"),
        @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        String query = "query { ToDoList { id title description assignee status dueDate } }";
        Map<String, Object> variables = Map.of();
        return forwardToTaskService(query, variables);
    }

    @Operation(summary = "Füge einen neuen Task hinzu", description = "Fügt einen neuen Task hinzu")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task hinzugefügt"),
        @ApiResponse(responseCode = "400", description = "Fehlerhafte Anfrage"),
        @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    })
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody Map<String, String> payload) {
        // Überprüfe, ob notwendige Felder vorhanden sind
        if (!payload.containsKey("title") || !payload.containsKey("description") || !payload.containsKey("assignee")) {
            return ResponseEntity.badRequest().body("Fehlende erforderliche Felder: title, description oder assignee.");
        }

        String title = payload.get("title");
        String description = payload.get("description");
        String assignee = payload.get("assignee");
        String status = payload.getOrDefault("status", "offen");
        String dueDate = payload.get("dueDate");

        String mutation = "mutation($title: String!, $description: String!, $assignee: String!, $status: String!, $dueDate: String!) " +
                          "{ addTask(title: $title, description: $description, assignee: $assignee, status: $status, dueDate: $dueDate) " +
                          "{ id title description assignee status dueDate } }";
        Map<String, Object> variables = Map.of(
                "title", title,
                "description", description,
                "assignee", assignee,
                "status", status,
                "dueDate", dueDate);

        return forwardToTaskService(mutation, variables);
    }
}
