package edu.fra.uas.Login_Management.model;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(){
        
    }
    
// Class constructor with parameters
    public User( String role, String firstName, String lastName, String email, String password) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;


// getters and setters
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
        return "User [role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email + ", password=" + password + "]";

    }
}
