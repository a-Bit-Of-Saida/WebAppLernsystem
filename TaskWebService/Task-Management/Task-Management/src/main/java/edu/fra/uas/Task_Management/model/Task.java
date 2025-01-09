package main.java.edu.fra.uas.Task_Management.model;

// the class named Task represents one Task with all it's attributes
public class Task {

    //current attributes of one task
    private Long id;
    private String title;       // task title
    private String description; // task description
    private String assignee;    // assigned person
   

    // Getters and Setters enable the possibility to change or access them 
    // Get-methods return the current value of an attribute
    public Long getId() {
        return id;
    }

    // Set-methods change and set the value of an attribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    

}
