package edu.fra.uas.Task_Management.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    // Defines a GraphQL query to get all tasks by user ID
    @QueryMapping(name = "tasksByUserId")
    public List<Task> getTasksByUserId(@Argument Long userId) {
        log.debug("🔍 getTasksByUserId() aufgerufen mit userId: " + userId); // Logs the method call with userId

        List<Task> allTasks = StreamSupport
                .stream(taskService.getAllTasks().spliterator(), false)
                .collect(Collectors.toList());

        // Logs all tasks in the database
        for (Task task : allTasks) {
            log.debug("📌 Task in DB: " + task.getTitle() + " | Assignee: " + task.getAssignee());
        }

        // Filters the tasks by the user ID

        return allTasks.stream()
                .filter(task -> task.getAssignee() != null && task.getAssignee().equals(userId))
                .collect(Collectors.toList());
    }

    //Defines a GraphQL query to get a task by its ID
    @QueryMapping(name = "taskById")
    public Task getTaskById(@Argument Long id) {
        log.debug("getTaskById() is called with id: " + id);
        Task task = taskService.getTaskById(id);
        if (task == null) {
            log.error("Task with id " + id + " not found");
        }
        return task;
    }
// Defines a GraphQL query to get all tasks due today
    @QueryMapping(name = "taskDueToday")
    public List<Task> getTasksDueToday() {
        log.debug("getTasksDueToday() is called");
        Iterable<Task> taskIter = taskService.getAllTasks();
        return StreamSupport.stream(taskIter.spliterator(), false)
                .filter(task -> task.getdueDate().equals(LocalDate.now().toString()))
                .collect(Collectors.toList());// Returns the list of tasks due today
    }
// Define a GraphQL mutation to add a new task
    @MutationMapping
    public Task addTask(@Argument String description, @Argument String title, @Argument String assignee,
            @Argument String status, @Argument String dueDate) {
        log.debug("addTask() is called"); // Logs the method call
        Task task = new Task();
        task.setDescription(description);
        task.setTitle(title);
        task.setAssignee(Long.valueOf(assignee));
        task.setStatus(status);
        task.setDueDate(dueDate);
        return taskService.createTask(task); // Create and return the new task
    }

    @MutationMapping
    public Task updateTask(@Argument Long id, @Argument String description, @Argument String title,
            @Argument String assignee, @Argument String status, @Argument String dueDate) {
        log.debug("updateTask() is called");
        Task task = taskService.getTaskById(id); //Retrieve the task by its ID
        if (task == null) {
            throw new RuntimeException("Task not found !"); // Throws an exception if the task is not found
        }
        // Update the tasks description if provided
        if (description != null && !description.isEmpty()) {
            task.setDescription(description);
        }
        // Update the tasks title if provided
        if (title != null && !title.isEmpty()) {
            task.setTitle(title);
        }
        // Update the tasks assignee if provided
        if (assignee != null && !assignee.isEmpty()) {
            task.setAssignee(Long.valueOf(assignee));
        }
        // Update the tasks status if provided
        if (status != null && !status.isEmpty()) {
            task.setStatus(status);
        }
        // Update the tasks due date if provided
        if (dueDate != null && !dueDate.isEmpty()) {
            task.setDueDate(dueDate);
        }
        return taskService.updateTask(task); // Updates and returns the task
    }

    @MutationMapping
    public Task deleteTask(@Argument Long id) {
        log.debug("deleteTask() is called"); // Logs the method call
        Task task = taskService.deleteTask(id);
        if (task != null) {
            taskService.deleteTask(id);
            return task; // Deletes and returns the task
        } else {
            throw new RuntimeException("Task not found !"); // Throws an exception if the task is not found
        }
    }
}