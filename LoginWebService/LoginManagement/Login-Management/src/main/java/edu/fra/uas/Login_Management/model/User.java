package edu.fra.uas.Login_Management.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    // User attributes
    private int id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {

    }

    // Class constructor with parameters
    public User(int id, String role, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email + ", password=" + password + "]";

    }
}
