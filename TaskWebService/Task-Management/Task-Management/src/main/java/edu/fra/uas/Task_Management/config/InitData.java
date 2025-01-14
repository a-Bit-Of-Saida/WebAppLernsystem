package edu.fra.uas.Task_Management.config;



//Import of necessary libraries
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.Task_Management.model.Task;
import edu.fra.uas.Task_Management.service.TaskService;
import jakarta.annotation.PostConstruct;
    
    //Class marked as a Spring-Component
    @Component
    public class InitData {
    
         //Initialization of a logger to log debug messages and track program flow
        private final Logger log = org.slf4j.LoggerFactory.getLogger(InitData.class);
        
        // Automatic dependency injection for the TaskService
        @Autowired
        TaskService taskService;
    
        // Execution after loading the Spring Application Context
        @PostConstruct
        public void init() {
            log.debug("### Initialize Data ###");
    
    
            // Creation and storage of the first task
            log.debug("create Task GPM Abgabe");
            Task task = new Task();
            task.setDescription("Gpm Aufgabe überarbeiten");
            task.setTitle("GPM Abgabe");
            task.setAssignee("Noel Krüger");
            task.setStatus("offen");
            task.setDueDate("2025-01-25");
            taskService.createTask(task);
    
            // Second task
            log.debug("create task Internetrecht Lernzettel");
            task = new Task();
            task.setDescription("Lernzettel fertigstellen");
            task.setTitle("Internetrecht Lernzettel");
            task.setAssignee("Merve Cicek");
            task.setStatus("In Bearbeitung");
            task.setDueDate("2025-01-20");
            taskService.createTask(task);
    
            //Third task
            log.debug("create task Projektplan Web");
            task = new Task();
            task.setDescription("Projektplan erstellen");
            task.setTitle("Projektplan Web");
            task.setAssignee("Joachim Yaprak");
            task.setStatus("abgeschlossen");
            task.setDueDate("2025-01-13"); 
            taskService.createTask(task);

        
            log.debug("### Data initialized ###");
        }
    
    }
    


































