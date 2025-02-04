package edu.fra.uas.Login_Management.loginService;

import org.springframework.stereotype.Service;

//Import the user model to use the variables, getters, and settes
import edu.fra.uas.Login_Management.model.User;
import java.util.ArrayList;

@Service
public class LoginService {

    // Array list for storing data
    private static final ArrayList<User> users = new ArrayList<>();

    // Initialize user data
    static {
        users.add(new User(1, "Student", "Merve", "Ciceck", "email@student.com", "password1234"));
        users.add(new User(2, "Prof", "User2", "User2", "email@prof.com", "password5678"));
        users.add(new User(3, "Student", "User3", "User3", "email@student2.com", "password5678"));
    }

    // Method to verify login credentials
    public User login(String role, String firstName, String lastName, String email, String password) {

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("login successful"); // Debugging-Log
                return u;
            }
        }
        System.out.println("login failed"); // Debugging-Log
        return null;
    }

}
