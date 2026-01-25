package com.company.controllers;

import com.company.models.Course;
import com.company.repositories.CourseRepository;

import java.util.List;

public class CourseController {
    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    // Create new course
    public void createCourse(String title, String description) {
        Course c = new Course();
        c.title = title;
        c.description = description;
        repo.create(c);
    }

    // Get all courses
    public List<Course> getCourses() {
        return repo.findAll();
    }

    // Get course by id
    public Course getCourse(int id) {
        return repo.findById(id);
    }
}
