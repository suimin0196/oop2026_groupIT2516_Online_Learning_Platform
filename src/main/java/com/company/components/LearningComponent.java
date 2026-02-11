package com.company.components;

import java.util.List;
import com.company.models.Lesson;
import com.company.services.LessonService;
import com.company.services.ProgressService;

// Component that groups lesson and progress functionality
public class LearningComponent {
    private final LessonService lessonService;
    private final ProgressService progressService;

    public LearningComponent(LessonService lessonService, ProgressService progressService) {
        this.lessonService = lessonService;
        this.progressService = progressService;
    }

    public Lesson getLesson(int id) {
        return lessonService.getById(id);
    }

    public List<Lesson> getAllLessons() {
        return lessonService.getAll();
    }

    public void markLessonCompleted(int userId, int lessonId) {
        progressService.markCompleted(userId, lessonId);
    }

    public int viewProgress(int userId, int courseId) {
        return progressService.viewProgress(userId, courseId);
    }
}
