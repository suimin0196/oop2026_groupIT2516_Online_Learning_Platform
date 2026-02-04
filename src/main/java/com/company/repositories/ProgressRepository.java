
package com.company.repositories;
public interface ProgressRepository {
    void markCompleted(int userId,int lessonId);
    int getProgress(int userId,int courseId);
}
