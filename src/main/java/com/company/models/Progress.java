
package com.company.models;
public class Progress {
    private int userId;
    private int courseId;
    private int completedLessons;
    public Progress(int userId,int courseId,int completedLessons){
        this.userId=userId; this.courseId=courseId; this.completedLessons=completedLessons;
    }
    public int getCompletedLessons(){return completedLessons;}
}
