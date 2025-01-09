package edu.fra.uas.Course_Management.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import edu.fra.uas.Course_Management.model.Course;
import edu.fra.uas.Course_Management.service.CourseService;

@Controller
@SchemaMapping(typeName = "Course")
public class CourseController {

    // Logger for logging information
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    // Automatic injection of the CourseService
    @Autowired
    private CourseService CourseService;

    /**
     * Query to get all courses.
     *
     * @return A list of all courses.
     */
    @QueryMapping(name = "allCourses")
    public List<Course> getAllCourses() {
        log.debug("getAllCourses() is called");
        Iterable<Course> coursesIter = CourseService.getAllCourses();
        List<Course> courses = new ArrayList<>();
        for (Course course : coursesIter) {
            courses.add(course);
        }
        return courses;
    }

}
