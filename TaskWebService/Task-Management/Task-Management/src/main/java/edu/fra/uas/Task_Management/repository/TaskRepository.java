package edu.fra.uas.Task_Management.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import edu.fra.uas.Task_Management.model.Task;

// Marks the class as aa Spring Repository
@Repository
public class TaskRepository extends HashMap<Long, Task> {  // This class extends the Hashmap to store tasks with their Ids as keys

}

























