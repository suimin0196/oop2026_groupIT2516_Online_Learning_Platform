
package com.company.services;
public interface ProgressService {
    void markCompleted(int userId,int lessonId);
    int viewProgress(int userId,int courseId);
}
