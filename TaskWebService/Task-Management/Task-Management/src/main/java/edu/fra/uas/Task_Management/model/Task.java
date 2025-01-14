package edu.fra.uas.Task_Management.model;

// Class Task represents a task including all necessary attributes
public class Task {

    // Attributes of a task
    private Long id;
    private String title;
    private String description;
    private String assignee;
    private String status;
    private String dueDate;


    // Getters and Setters to access and modify the attributes

    //Get methods return the current value of an attribute
    public Long getId() {
        return id;
    }

    //Set methods set a new value for an attribute
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

    public void setStatus(String status) {
        this.status = status;
     
    }
    
    public String getStatus(){
        return status;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
     
    }

    public String getdueDate(){
        return dueDate;
    }

}
