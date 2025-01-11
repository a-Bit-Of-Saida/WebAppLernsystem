package edu.fra.uas.Task_Management.model;

    // Class Task represents a task including all properties
    public class Task {
    
        // Attributes of the task
        private Long id;
        private String title;
        private String description;
        private String assignee;
        
        // Getters and Setters to access and modify the attributes

        // Get methods return the current value of an attribute
        public Long getId() {
            return id;
        }
    
        // Set methodes set the value of an attribute
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
    





































