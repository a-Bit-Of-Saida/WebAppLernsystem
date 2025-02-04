package edu.fra.uas.Login_Management.loginController;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
            return ResponseEntity.ok(Collections.singletonMap("message", "login successful"));
        } else {
            // Return error message if login is unsuccessful
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "login invalid/unsuccessful"));
        }
    }

}
