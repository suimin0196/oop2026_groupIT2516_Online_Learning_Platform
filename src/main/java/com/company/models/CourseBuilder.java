package com.company.models;

import java.util.List;

// Builder pattern for constructing Course objects
// Allows flexible and readable creation of Course instances with various configurations
public class CourseBuilder {
    private String title;
    private String description;
    private List<Lesson> lessons;
    private List<String> tags;

    public CourseBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }

    public CourseBuilder setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    // Build method to create the Course object
    public Course build() {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Course title is required");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Course description is required");
        }

        Course course = new Course();
        course.title = this.title;
        course.description = this.description;
        course.lessons = this.lessons;
        course.tags = this.tags;
        return course;
    }
}