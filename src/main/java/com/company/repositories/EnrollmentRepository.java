package com.company.repositories;

import com.company.models.Course;
import java.util.List;

public interface EnrollmentRepository {

    // Enroll user to course
    void enroll(int userId, int courseId);

    // Get all courses a user is enrolled in
    List<Course> findCoursesByUserId(int userId);
    boolean isEnrolled(int userId, int courseId);

}
