package com.company.models;

// Represents a quiz lesson
public class QuizLesson extends Lesson {
    private int totalQuestions;
    private int passingScore;

    public QuizLesson() {
        super();
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    @Override
    public String toString() {
        return "QuizLesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", totalQuestions=" + totalQuestions +
                ", passingScore=" + passingScore +
                '}';
    }
}
