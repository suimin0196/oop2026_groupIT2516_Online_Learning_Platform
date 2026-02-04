package com.company.services;

import com.company.models.Course;

// CourseService extends BaseService for common service operations
public interface CourseService extends BaseService<Course, Integer> {
    
    // Enroll a user in a course
    void enroll(int userId, int courseId);
}
