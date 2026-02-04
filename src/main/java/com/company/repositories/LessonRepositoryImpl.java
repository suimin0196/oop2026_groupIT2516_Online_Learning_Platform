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
    @Override
    public Lesson findById(Integer lessonId) {
        String sql = "SELECT * FROM lessons WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Lesson l = new Lesson();
                l.id = rs.getInt("id");
                l.courseId = rs.getInt("course_id");
                l.title = rs.getString("title");
                return l;
            } else {
                return null; // Or throw an exception if preferred
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching lesson", e);
        }
    }

    // Implement BaseRepository methods

    @Override
    public boolean exists(Integer id) {
        String sql = "SELECT 1 FROM lessons WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if lesson exists", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM lessons WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting lesson", e);
        }
    }

    @Override
    public void update(Lesson lesson) {
        String sql = "UPDATE lessons SET course_id = ?, title = ? WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lesson.courseId);
            ps.setString(2, lesson.title);
            ps.setInt(3, lesson.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating lesson", e);
        }
    }

    @Override
    public List<Lesson> findAll() {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lessons";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.id = rs.getInt("id");
                l.courseId = rs.getInt("course_id");
                l.title = rs.getString("title");
                lessons.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all lessons", e);
        }
        return lessons;
    }
}
