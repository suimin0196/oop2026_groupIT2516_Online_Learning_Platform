package com.company.repositories;

import com.company.models.Lesson;
import java.util.List;

public interface LessonRepository {

    // Create lesson
    void create(Lesson lesson);

    // Get lessons by course id
    List<Lesson> findByCourseId(int courseId);

    Lesson getLesson(int lessonId);
}
