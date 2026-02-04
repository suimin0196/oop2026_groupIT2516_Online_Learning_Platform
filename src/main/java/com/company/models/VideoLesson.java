package com.company.models;

// Represents a video lesson
public class VideoLesson extends Lesson {
    private String videoUrl;
    private int durationMinutes;

    public VideoLesson() {
        super();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "VideoLesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}
