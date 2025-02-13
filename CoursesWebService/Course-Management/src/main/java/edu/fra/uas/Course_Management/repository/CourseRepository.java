package edu.fra.uas.Course_Management.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import edu.fra.uas.Course_Management.model.Course;

@Repository
public class CourseRepository extends HashMap<Long, Course> {
// The courses are stored in the HashMap. The key is the ID of the course (as long).
}
