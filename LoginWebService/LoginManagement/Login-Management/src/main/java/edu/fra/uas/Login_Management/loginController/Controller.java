package edu.fra.uas.Login_Management.loginController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.Login_Management.loginService.LoginService;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class Controller {

    private LoginService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String role,@RequestParam String firstName, @RequestParam String lasName, @RequestParam String email, @RequestParam String password) {
        //boolean isValidUser = authService.login(email, password);
        boolean isValidUser = authService.login( role,  firstName, lasName,email,password);

        if (isValidUser) {
            return ResponseEntity.ok("login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login invalid/unsuccessful");
        }
        
    }
}
