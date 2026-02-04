package com.company.services;

// ProgressService for progress-specific operations
public interface ProgressService {
    
    // Mark a lesson as completed for a user
    void markCompleted(int userId, int lessonId);
    
    // View progress of a user in a course
    int viewProgress(int userId, int courseId);
}
