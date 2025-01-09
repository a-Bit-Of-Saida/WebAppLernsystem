package edu.fra.uas.Course_Management.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import edu.fra.uas.Course_Management.model.Course;

@Repository
public class CourseRepository extends HashMap<Long, Course> {
//In die HashMap werden die Kurse gespeichert. Der Schlüssel ist die ID des Kurses (als long).
}
