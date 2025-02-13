package edu.fra.uas.Login_Management.loginController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.Login_Management.loginService.LoginService;
import edu.fra.uas.Login_Management.model.User;

@CrossOrigin(origins = "http://localhost:3000") // allow requests from React frontend
@RestController
@RequestMapping("/auth")
public class Controller {

    @Autowired
    private LoginService authService; // Automatic injection of LoginService

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User loginRequest) {
        System.out.println("Received login request: " + loginRequest); // Debugging-Log

        // Verify login credentials
        User user = authService.login(
                loginRequest.getRole(),
                loginRequest.getFirstName(),
                loginRequest.getLastName(),
                loginRequest.getEmail(),
                loginRequest.getPassword());

        // Return user ID if login is successful
        if (user != null) {
            return ResponseEntity.ok(Map.of("message", "login successful", "userId", user.getId()));
        } else {
            // Return error message if login is unsuccessful
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "login invalid/unsuccessful"));
        }
    }

    // Create a new user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = authService.createUser(
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Get a user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = authService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = authService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Update a user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = authService.updateUser(
                id,
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword());
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id) {
        boolean isDeleted = authService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok(Collections.singletonMap("message", "User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User not found"));
        }
    }
}