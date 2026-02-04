
package com.company.services.impl;

import com.company.services.ProgressService;
import com.company.repositories.ProgressRepository;
import com.company.repositories.EnrollmentRepository;
import com.company.exceptions.UserNotEnrolledException;

public class ProgressServiceImpl implements ProgressService {
    private ProgressRepository progressRepo;
    private EnrollmentRepository enrollmentRepo;

    public ProgressServiceImpl(ProgressRepository p, EnrollmentRepository e){
        this.progressRepo=p;
        this.enrollmentRepo=e;
    }

    @Override
    public void markCompleted(int userId,int lessonId){
        if(!enrollmentRepo.isEnrolled(userId, lessonId))
            throw new UserNotEnrolledException("User not enrolled");
        progressRepo.markCompleted(userId, lessonId);
    }

    @Override
    public int viewProgress(int userId,int courseId){
        return progressRepo.getProgress(userId, courseId);
    }
}
