package edu.fra.uas.Course_Management.controller;

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

    // The GraphQL mutation for adding a file to a course
    @MutationMapping
    public Course addFileToCourse(@Argument Long id, @Argument String fileName, @Argument String fileDescription) {
        log.debug("addFileToCourse() is called");
        return CourseService.addFileToCourse(id, fileName, fileDescription);
    }


    // The GraphQL query for retrieving a course by its ID
    @QueryMapping(name="courseById")
    public Course getCourseById(@Argument Long id) {
        log.debug("getCourseById() is called");
        return CourseService.getCourseById(id);
    }

}
