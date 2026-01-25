package com.company.repositories;

import com.company.db.IDB;
import com.company.models.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonRepositoryImpl implements LessonRepository {

    private final IDB db;

    public LessonRepositoryImpl(IDB db) {
        this.db = db;
    }

    // Insert new lesson
    @Override
    public void create(Lesson lesson) {
        String sql = "INSERT INTO lessons(course_id, title) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lesson.courseId);
            ps.setString(2, lesson.title);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error creating lesson", e);
        }
    }

    // Retrieve lessons for a specific course
    @Override
    public List<Lesson> findByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lessons WHERE course_id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Lesson l = new Lesson();
                l.id = rs.getInt("id");
                l.courseId = rs.getInt("course_id");
                l.title = rs.getString("title");
                lessons.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching lessons", e);
        }
        return lessons;
    }
}
