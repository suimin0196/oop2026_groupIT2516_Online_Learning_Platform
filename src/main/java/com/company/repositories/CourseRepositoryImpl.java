package com.company.repositories;

import com.company.db.IDB;
import com.company.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {
    private final IDB db;

    public CourseRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO courses(title, description) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, course.title);
            ps.setString(2, course.description);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating course", e);
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Course c = new Course();
                c.id = rs.getInt("id");
                c.title = rs.getString("title");
                c.description = rs.getString("description");
                courses.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching courses", e);
        }
        return courses;
    }

    @Override
    public Course findById(Integer id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Course c = new Course();
                c.id = rs.getInt("id");
                c.title = rs.getString("title");
                c.description = rs.getString("description");
                return c;
            }
            throw new RuntimeException("Course not found");
        } catch (SQLException e) {
            throw new RuntimeException("Error finding course", e);
        }
    }

    @Override
    public boolean exists(Integer id) {
        String sql = "SELECT 1 FROM courses WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if course exists", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Course not found for deletion");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting course", e);
        }
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE courses SET title = ?, description = ? WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, course.title);
            ps.setString(2, course.description);
            ps.setInt(3, course.id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Course not found for update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating course", e);
        }
    }
    // Retrieve only course description
    @Override
    public String getDescriptionById(int id) {

        String sql = "SELECT description FROM courses WHERE id = ?";

        try (Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("description");
            }

            throw new RuntimeException("Course not found");

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving course description", e);
        }
    }
    @Override
    public boolean isArchived(int id) {
        String sql = "SELECT archived FROM courses WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("archived");
            }

            throw new RuntimeException("Course not found");

        } catch (SQLException e) {
            throw new RuntimeException("Error checking if course is archived", e);
        }
    }

}
