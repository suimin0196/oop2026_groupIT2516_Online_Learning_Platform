package com.company.models;

// Represents a text-based lesson
public class TextLesson extends Lesson {
    private String content;
    private int wordCount;

    public TextLesson() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.wordCount = content != null ? content.split("\\s+").length : 0;
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public String toString() {
        return "TextLesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", wordCount=" + wordCount +
                '}';
    }
}
