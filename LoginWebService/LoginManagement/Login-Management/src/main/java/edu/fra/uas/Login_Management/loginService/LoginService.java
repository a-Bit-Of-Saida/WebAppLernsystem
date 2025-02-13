package edu.fra.uas.Login_Management.loginService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.fra.uas.Login_Management.model.User;

@Service
public class LoginService {

    // Array list for storing data
    private static final ArrayList<User> users = new ArrayList<>();

    // Initialize user data
    static {
        users.add(new User(1, "Student", "Merve", "Ciceck", "email@student.com", "password1234"));
        users.add(new User(2, "Admin", "Schneider", "Richt", "email@admin.com", "password5678"));
        users.add(new User(3, "Student", "Leon", "Schmit", "email@student2.com", "password9876"));
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

    // Method to create a new user
    public User createUser(String role, String firstName, String lastName, String email, String password) {
        int newId = users.size() + 1;
        User newUser = new User(newId, role, firstName, lastName, email, password);
        users.add(newUser);
        return newUser;
    }

    // Method to get a user by ID
    public User getUser(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // Method to update a user
    public User updateUser(int id, String role, String firstName, String lastName, String email, String password) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setRole(role);
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setEmail(email);
                u.setPassword(password);
                return u;
            }
        }
        return null;
    }

    // Method to delete a user
    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}