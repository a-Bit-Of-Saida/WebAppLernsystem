package edu.fra.uas.Course_Management.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.Course_Management.model.Course;
import edu.fra.uas.Course_Management.model.File;
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
    // Variable to manage the ID of the files
    private long nextFileId = 1;

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

    /**
     * Adds a file to a course.
     *
     * @return The updated course.
     */
    public Course addFileToCourse(long courseId, String fileName, String fileDescription) {
        Course course = courseRepository.get(courseId);
        if (course != null) {
            File file = new File(nextFileId++, fileName, fileDescription);
            course.addFile(file);
            courseRepository.put(courseId, course);
        }
        return course;
    }

    /**
     * Retrieves a course by its ID.
     *
     * @return The course with the specified ID.
     */
    public Course getCourseById(long id) {
        log.debug("getCourse: " + id);
        return courseRepository.get(id);
    }

    /**
     * Retrieves a course by its name.
     *
     * @param name The name of the course.
     */
    public Course getCourseByName(String name) {
        log.debug("getCourseByName: " + name);
        for (Course course : courseRepository.values()) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to be deleted.
     */
    public Course deleteCourse(long id) {
        log.debug("deleteCourse: " + id);
        return courseRepository.remove(id);
    }
}
