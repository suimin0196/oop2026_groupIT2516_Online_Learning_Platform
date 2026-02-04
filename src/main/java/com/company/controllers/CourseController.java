package com.company.controllers;

import com.company.models.Course;
import com.company.models.CourseBuilder;
import com.company.models.Lesson;
import com.company.repositories.CourseRepository;

import java.util.List;

public class CourseController {
    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    // Create new course
    public void createCourse(String title, String description, List<Lesson> lessons, List<String> tags) {
        Course c = new CourseBuilder()
            .setTitle(title)
            .setDescription(description)
            .setLessons(lessons)
            .setTags(tags)
            .build();
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
