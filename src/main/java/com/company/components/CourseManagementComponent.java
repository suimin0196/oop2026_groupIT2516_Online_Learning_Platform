package com.company.components;

import java.util.List;
import com.company.models.Course;
import com.company.models.Lesson;
import com.company.controllers.CourseController;
import com.company.services.CourseService;

// Component that groups course-related functionality (controller + service)
public class CourseManagementComponent {
    private final CourseService courseService;
    private final CourseController courseController;

    public CourseManagementComponent(CourseService courseService, CourseController courseController) {
        this.courseService = courseService;
        this.courseController = courseController;
    }

    public void createCourse(String title, String description, List<Lesson> lessons, List<String> tags) {
        courseController.createCourse(title, description, lessons, tags);
    }

    public List<Course> listCourses() {
        return courseService.getAll();
    }

    public Course getCourse(int id) {
        return courseService.getById(id);
    }

    public void enrollUser(int userId, int courseId) {
        courseService.enroll(userId, courseId);
    }
}
