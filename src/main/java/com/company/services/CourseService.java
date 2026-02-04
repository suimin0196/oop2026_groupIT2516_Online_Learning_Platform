
package com.company.services;
import com.company.models.Course;
public interface CourseService {
    void enroll(int userId, int courseId);
    Course getCourse(int id);
}
