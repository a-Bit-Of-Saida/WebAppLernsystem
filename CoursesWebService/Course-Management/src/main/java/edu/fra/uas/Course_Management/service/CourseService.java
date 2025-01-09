package edu.fra.uas.Course_Management.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.Course_Management.model.Course;
import edu.fra.uas.Course_Management.repository.CourseRepository;

@Service
public class CourseService {

    // Logger for logging information
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CourseService.class);

    // Automatic injection of the CourseRepository
    @Autowired
    private CourseRepository courseRepository;

    // Variable to manage the ID of the courses
    private long nextCourseId = 1;

    /**
     * Creates a new course and adds it to the repository.
     *
     * @param course The course object to be created.
     */
    public Course createCourse(Course course) {
        // Sets the ID of the course and adds 1 to the nextCourseId.
        course.setId(nextCourseId++);
        // Adds the course to the repository.
        log.debug("createCourse: " + course);
        courseRepository.put(course.getId(), course);
        // Returns the created course
        return courseRepository.get(course.getId());
    }

     /**
     * Retrieves all courses from the repository.
     *
     * @return An iterable collection of all courses.
     */
    
    public Iterable<Course> getAllCourses() {
        log.debug("getAllCourses");
        return courseRepository.values();
    }
}
