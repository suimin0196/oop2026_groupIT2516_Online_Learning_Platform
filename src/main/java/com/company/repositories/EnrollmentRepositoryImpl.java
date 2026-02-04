package com.company.repositories;

import com.company.db.IDB;
import com.company.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final IDB db;

    public EnrollmentRepositoryImpl(IDB db) {
        this.db = db;
    }

    // Enroll user into a course
    @Override
    public void enroll(int userId, int courseId) {
        String sql = "INSERT INTO enrollments(user_id, course_id) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Enrollment failed (user may already be enrolled)", e);
        }
    }

    // Get all courses a user is enrolled in
    @Override
    public List<Course> findCoursesByUserId(int userId) {

        List<Course> courses = new ArrayList<>();

        String sql = "SELECT c.id, c.title, c.description FROM courses c JOIN enrollments e ON c.id = e.course_id WHERE e.user_id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.id = rs.getInt("id");
                c.title = rs.getString("title");
                c.description = rs.getString("description");
                courses.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled courses", e);
        }

        return courses;
    }
    @Override
    public boolean isEnrolled(int userId, int courseId) {
        String sql = "SELECT 1 FROM enrollments WHERE user_id=? AND course_id=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking enrollment status", e);
        }
        return false;
    }
}
