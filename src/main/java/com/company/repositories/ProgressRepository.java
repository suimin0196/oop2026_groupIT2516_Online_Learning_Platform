package com.company.repositories;

// ProgressRepository for progress-specific operations
public interface ProgressRepository {
    
    // Mark a lesson as completed for a user
    void markCompleted(int userId, int lessonId);
    
    // Get overall progress for a user in a course
    int getProgress(int userId, int courseId);
}
