package edu.fra.uas.Course_Management.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Course with its details and associated files.
 */

public class Course {

    private long id;
    private String name;
    private String description;
    private String instructor;
    private List<File> files = new ArrayList<>();

    public Course(String name, String description, String instructor) {
        this.name = name;
        this.description = description;
        this.instructor = instructor;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    // Adds a file to the course 
    public void addFile(File file) {
        this.files.add(file);
    }

    // Removes a file from the course by its id
    public void removeFileById(long fileId) {
        this.files.removeIf(file -> file.getId() == fileId);
    }
}
