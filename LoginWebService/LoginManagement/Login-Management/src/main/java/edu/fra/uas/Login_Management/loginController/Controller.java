package edu.fra.uas.Login_Management.loginController;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.Login_Management.loginService.LoginService;
import edu.fra.uas.Login_Management.model.User;

@RestController
@RequestMapping("/auth")
public class Controller {

    @Autowired
    private LoginService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest) {
        boolean isValidUser = authService.login(
                loginRequest.getRole(),
                loginRequest.getFirstName(),
                loginRequest.getLastName(),
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (isValidUser) {
            return ResponseEntity.ok(Collections.singletonMap("message", "login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "login invalid/unsuccessful"));
        }
    }

}
