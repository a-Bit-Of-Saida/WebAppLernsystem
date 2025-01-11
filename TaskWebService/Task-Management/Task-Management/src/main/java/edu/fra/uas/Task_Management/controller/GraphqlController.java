package edu.fra.uas.Task_Management.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import edu.fra.uas.Task_Management.model.Task;
import edu.fra.uas.Task_Management.service.TaskService;
    
    // Marks the class as a Spring-Controller
    @Controller
    @SchemaMapping(typeName = "Task")
    public class GraphqlController {
    
        private static final Logger log = LoggerFactory.getLogger(GraphqlController.class);
    
        @Autowired
        private TaskService taskService;
    
        // Define a GraphQL query for the "ToDOList"
        @QueryMapping(name="ToDoList")
        public List<Task> ToDoList() {
            log.debug("ToDoList() is called"); // Logs the method call
            Iterable<Task> taskIter = taskService.getAllTasks(); // Retrieves all tasks from the service
            List<Task> tasks = new ArrayList<>();
            for (Task task : taskIter) {
                tasks.add(task); // Adds the tasks to the list
            }
            return tasks; // Returns the list
        }
    
    }
    





































