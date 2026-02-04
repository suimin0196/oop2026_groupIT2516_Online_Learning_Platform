
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
    public void create(Lesson lesson) {
        lessonRepo.create(lesson);
    }

    @Override
    public boolean exists(Integer id) {
        return lessonRepo.exists(id);
    }

    @Override
    public Lesson getById(Integer id) {
        Lesson lesson = lessonRepo.findById(id);
        if (lesson == null) throw new LessonNotFoundException("Lesson not found");
        return lesson;
    }

    @Override
    public void delete(Integer id) {
        lessonRepo.delete(id);
    }

    @Override
    public java.util.List<Lesson> getAll() {
        return lessonRepo.findAll();
    }

    @Override
    public void update(Lesson lesson) {
        lessonRepo.update(lesson);
    }
}
