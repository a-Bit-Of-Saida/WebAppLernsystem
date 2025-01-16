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

    // The GraphQL query for retrieving a course by its ID
    @QueryMapping(name = "courseById")
    public Course getCourseById(@Argument long id) {
        log.debug("getCourseById() is called");
        return CourseService.getCourseById(id);
    }

    /**
     * Query to get a course by its name.
     *
     * @param name The name of the course.
     */
    @QueryMapping(name = "courseByName")
    public Course getCourseByName(@Argument String name) {
        log.debug("getCourseByName() is called");
        return CourseService.getCourseByName(name);
    }

    // The GraphQL mutation for adding a file to a course
    @MutationMapping
    public Course addFileToCourse(@Argument long id, @Argument String fileName, @Argument String fileDescription) {
        log.debug("addFileToCourse() is called");
        return CourseService.addFileToCourse(id, fileName, fileDescription);
    }

    // The GraphQL mutation for deleting a file from a course
    @MutationMapping
    public Course deleteFileFromCourse(@Argument long id, @Argument long fileId) {
        log.debug("deleteFileFromCourse() is called");
        return CourseService.deleteFileFromCourse(id, fileId);
    }

    /**
     * Mutation to add a new course.
     *
     * @param description The description of the course.
     * @param name The name of the course.
     * @param instructor The instructor of the course.
     */
    @MutationMapping
    public Course addCourse(@Argument String description, @Argument String name, @Argument String instructor) {
        log.debug("addUser() is called");
        Course course = new Course(description, name, instructor);
        return CourseService.createCourse(course);
    }

    /**
     * Mutation to delete a course by its ID.
     *
     * @param id The ID of the course to be deleted.
     */
    @MutationMapping
    public Course deleteCourse(@Argument long id) {
        log.debug("deleteCourse() is called");
        Course course = CourseService.getCourseById(id);
        if (course != null) {
            CourseService.deleteCourse(id);
            log.debug("delete course");
            return course;
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    // The GraphQL mutation for updating an existing course
    @MutationMapping
    public Course updateCourse(@Argument long id, @Argument String description, @Argument String name, @Argument String instructor) {
        log.debug("updateCourse() is called");
        Course course = CourseService.getCourseById(id);
        // Check if the course exists
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        // Update the course description
        if (description != null && !description.isEmpty()) {
            course.setDescription(description);
        }
        // Update the course name
        if (name != null && !name.isEmpty()) {
            course.setName(name);
        }
        // Update the course instructor
        if (instructor != null && !instructor.isEmpty()) {
            course.setInstructor(instructor);
        }
        return CourseService.updateCourse(id, course);
    }
}
