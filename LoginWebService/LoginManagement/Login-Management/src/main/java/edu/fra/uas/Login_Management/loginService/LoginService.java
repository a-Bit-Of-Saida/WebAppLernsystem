package edu.fra.uas.Login_Management.loginService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import edu.fra.uas.Login_Management.model.User;
import java.util.ArrayList;

@Service
public class LoginService {

    @Autowired
    private User user;

    private static final ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("Student","User1", "User1","email@student.com", "password1234"));
        users.add(new User("Prof","User2","User2", "email@prof.com", "password5678"));
    }

    
    public boolean login(String role, String firstName, String lastName, String email, String password) {
        
        for (User u : users) {
            if (u.getRole().equals(role) && u.getFirstName().equals(firstName)&& u.getLastName().equals(lastName)&& u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("login successful");
                return true;
            }
        }
        System.out.println("login failed");
        return false;
    }

}

