
package com.company.services.impl;

import com.company.services.LessonService;
import com.company.services.AbstractBaseService;
import com.company.repositories.LessonRepository;
import com.company.models.Lesson;
import com.company.exceptions.LessonNotFoundException;

public class LessonServiceImpl extends AbstractBaseService<Lesson, Integer> implements LessonService {

    public LessonServiceImpl(LessonRepository repo){
        super(repo);
    }

    @Override
    public Lesson getById(Integer id) {
        Lesson lesson = repo.findById(id);
        if (lesson == null) throw new LessonNotFoundException("Lesson not found");
        return lesson;
    }
}
