package edu.fra.uas.Task_Management.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.Task_Management.model.Task;
import edu.fra.uas.Task_Management.repository.TaskRepository;

/**
 * This class represents the service for the user.
 */
@Service
public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TaskService.class);

    public List<Task> getTasksDueToday() {
        log.debug("getTasksDueToday");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Task> tasksDueToday = new ArrayList<>();
        for (Task task : taskRepository.values()) {
            if (task.getdueDate() != null && LocalDate.parse(task.getdueDate(), formatter).equals(today)) {
                tasksDueToday.add(task);
            }
        }
        return tasksDueToday;
    }

    @Autowired
    private TaskRepository taskRepository;

    private long nextTaskId = 1;

    // Method to create a new task
    public Task createTask(Task user) {
        user.setId(nextTaskId++);
        log.debug("createTask: " + user);
        taskRepository.put(user.getId(), user);
        return taskRepository.get(user.getId());
    }

    // Method to retrieve all tasks
    public Iterable<Task> getAllTasks() {
        log.debug("getAllTasks"); // Logs the retrieval of all tasks
        return taskRepository.values(); // Returns all tasks from the repository
    }

    // Method to call each task through their id
    public Task getTaskById(long id) {
        log.debug("getTask: " + id);
        return taskRepository.get(id);
    }

    // Method to update the status of each task
    public Task updateTask(Task user) {
        log.debug("updateTask: " + user);
        taskRepository.put(user.getId(), user);
        return taskRepository.get(user.getId());
    }

    // Method to delete a task through their id
    public Task deleteTask(long id) {
        log.debug("deleteTask: " + id);
        return taskRepository.remove(id);
    }

}
