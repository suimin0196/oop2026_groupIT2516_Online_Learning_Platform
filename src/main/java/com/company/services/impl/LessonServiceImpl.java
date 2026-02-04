
package com.company.services.impl;

import com.company.services.LessonService;
import com.company.repositories.LessonRepository;
import com.company.models.Lesson;
import com.company.exceptions.LessonNotFoundException;

public class LessonServiceImpl implements LessonService {
    private LessonRepository lessonRepo;

    public LessonServiceImpl(LessonRepository repo){
        this.lessonRepo=repo;
    }

    @Override
    public Lesson openLesson(int lessonId){
        Lesson lesson = lessonRepo.getLesson(lessonId);
        if(lesson==null) throw new LessonNotFoundException("Lesson not found");
        return lesson;
    }
}
