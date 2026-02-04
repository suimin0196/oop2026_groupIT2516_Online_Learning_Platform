
package com.company.services.impl;

import com.company.services.CourseService;
import com.company.repositories.EnrollmentRepository;
import com.company.repositories.CourseRepository;
import com.company.models.Course;
import com.company.exceptions.CourseArchivedException;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepo;
    private EnrollmentRepository enrollmentRepo;

    public CourseServiceImpl(CourseRepository c, EnrollmentRepository e){
        this.courseRepo=c;
        this.enrollmentRepo=e;
    }

    @Override
    public void enroll(int userId, int courseId){
        if(courseRepo.isArchived(courseId)) throw new CourseArchivedException("Course is archived");
        enrollmentRepo.enroll(userId, courseId);
    }

    // Implementing BaseService methods

    @Override
    public void create(Course course) {
        courseRepo.create(course);
    }

    @Override
    public boolean exists(Integer id) {
        return courseRepo.exists(id);
    }

    @Override
    public Course getById(Integer id) {
        return courseRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        courseRepo.delete(id);
    }

    @Override
    public java.util.List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Override
    public void update(Course course) {
        courseRepo.update(course);
    }
}
