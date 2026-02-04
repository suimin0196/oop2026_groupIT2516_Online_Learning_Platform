package com.company.repositories;

import com.company.models.Lesson;
import java.util.List;

// LessonRepository extends BaseRepository for common CRUD operations
public interface LessonRepository extends BaseRepository<Lesson, Integer> {
    
    // Get lessons by course id
    List<Lesson> findByCourseId(int courseId);
}
