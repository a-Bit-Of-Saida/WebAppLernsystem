package edu.fra.uas.Course_Management.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.Course_Management.model.Course;
import edu.fra.uas.Course_Management.service.CourseService;
import jakarta.annotation.PostConstruct;

@Component
public class InitData {

    //Logger for logging information
    private final Logger log = org.slf4j.LoggerFactory.getLogger(InitData.class);

    // Automatic injection of CourseService
    @Autowired
    CourseService courseService;

    // This method is called after the bean is constructed
    @PostConstruct
    public void init() {
        log.debug("### Initialize Data ###");

        // Create and add a sample course
        log.debug("create course Algebra");
        Course course = new Course("Algebra", "Mathe", "Herr Müller");
        courseService.createCourse(course);

        // Add a file to the course "Algebra"
        log.debug("add file to course Algebra");
        courseService.addFileToCourse(course.getId(), "Algebra Präsentation", "Dies ist eine Präsentation zum Thema Algebra.");

        log.debug("create course Analysis");
        Course course2 = new Course("Analysis", "Mathe", "Frau Schmidt");
        courseService.createCourse(course2);

        log.debug("### Data initialized ###");
    }
}
