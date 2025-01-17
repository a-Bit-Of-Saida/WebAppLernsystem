package edu.fra.uas.Task_Management.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
    @QueryMapping(name = "ToDoList")
    public List<Task> ToDoList() {
        log.debug("ToDoList() is called"); // Logs the method call
        Iterable<Task> taskIter = taskService.getAllTasks(); // Retrieves all tasks from the service
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskIter) {
            tasks.add(task); // Adds the tasks to the list
        }
        return tasks; // Returns the list
    }

    @QueryMapping(name = "taskById")
    public Task getTaskById(@Argument Long id) {
        log.debug("getTaskById() is called with id: " + id);
        Task task = taskService.getTaskById(id);
        if (task == null) {
            log.error("Task with id " + id + " not found");
        }
        return task;
    }

    @QueryMapping(name = "taskDueToday")
    public List<Task> taskDueToday() {
        log.debug("taskDueToday() is called");
        return taskService.getTasksDueToday();
    }

    @MutationMapping
    public Task addTask(@Argument String description, @Argument String title, @Argument String assignee,
            @Argument String status, @Argument String dueDate) {
        log.debug("addTask() is called");
        Task task = new Task();
        task.setDescription(description);
        task.setTitle(title);
        task.setAssignee(assignee);
        task.setStatus(status);
        task.setDueDate(dueDate);
        return taskService.createTask(task);
    }

    @MutationMapping
    public Task updateTask(@Argument Long id, @Argument String description, @Argument String title,
            @Argument String assignee, @Argument String status, @Argument String dueDate) {
        log.debug("updateTask() is called");
        Task task = taskService.getTaskById(id);
        if (task == null) {
            throw new RuntimeException("Task not found !");
        }
        if (description != null && !description.isEmpty()) {
            task.setDescription(description);
        }
        if (title != null && !title.isEmpty()) {
            task.setTitle(title);
        }
        if (assignee != null && !assignee.isEmpty()) {
            task.setAssignee(assignee);
        }
        if (status != null && !status.isEmpty()) {
            task.setStatus(status);
        }
        if (dueDate != null && !dueDate.isEmpty()) {
            task.setDueDate(dueDate);
        }
        return taskService.updateTask(task);
    }

    @MutationMapping
    public Task deleteTask(@Argument Long id) {
        log.debug("deleteTask() is called");
        Task task = taskService.deleteTask(id);
        if (task != null) {
            taskService.deleteTask(id);
            return task;
        } else {
            throw new RuntimeException("Task not found !");
        }
    }
}