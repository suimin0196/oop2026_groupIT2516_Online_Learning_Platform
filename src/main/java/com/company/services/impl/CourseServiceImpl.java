
package com.company.services.impl;

import com.company.services.CourseService;
import com.company.services.AbstractBaseService;
import com.company.repositories.EnrollmentRepository;
import com.company.repositories.CourseRepository;
import com.company.models.Course;
import com.company.exceptions.CourseArchivedException;

public class CourseServiceImpl extends AbstractBaseService<Course, Integer> implements CourseService {
    private final EnrollmentRepository enrollmentRepo;

    public CourseServiceImpl(CourseRepository c, EnrollmentRepository e){
        super(c);
        this.enrollmentRepo = e;
    }

    @Override
    public void enroll(int userId, int courseId){
        // Delegate archival check to repository-specific method
        if(((CourseRepository) repo).isArchived(courseId)) throw new CourseArchivedException("Course is archived");
        enrollmentRepo.enroll(userId, courseId);
    }
}
