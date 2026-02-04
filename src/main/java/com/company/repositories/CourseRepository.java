package com.company.repositories;

import com.company.models.Course;
import java.util.List;

public interface CourseRepository {
    // Save new course
    void create(Course course);

    // List all courses
    List<Course> findAll();

    // Find course by id
    Course findById(int id);
    // Get course description by id
    String getDescriptionById(int id);
    
    boolean isArchived(int id);

}
