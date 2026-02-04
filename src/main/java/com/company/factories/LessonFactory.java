package com.company.factories;

import com.company.models.Lesson;
import com.company.models.VideoLesson;
import com.company.models.TextLesson;
import com.company.models.QuizLesson;

// Factory pattern for creating different types of lessons
// Provides a central point for instantiating lesson subtypes
public class LessonFactory {
    
    // Factory method to create lesson instances based on type
    public static Lesson createLesson(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Lesson type cannot be null or empty");
        }

        switch (type.toLowerCase()) {
            case "video":
                return new VideoLesson();
            case "text":
                return new TextLesson();
            case "quiz":
                return new QuizLesson();
            default:
                throw new IllegalArgumentException("Unknown lesson type: " + type + 
                        ". Supported types are: Video, Text, Quiz");
        }
    }
}