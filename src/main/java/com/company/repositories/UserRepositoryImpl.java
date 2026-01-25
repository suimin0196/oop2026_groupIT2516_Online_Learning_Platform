package com.company.repositories;

import com.company.db.IDB;
import com.company.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final IDB db;

    public UserRepositoryImpl(IDB db) {
        this.db = db;
    }

    // Insert new user into database
    @Override
    public void create(User user) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.name);
            ps.setString(2, user.email);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    // Retrieve all users
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.name = rs.getString("name");
                u.email = rs.getString("email");
                users.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users", e);
        }
        return users;
    }

    // Find user by id
    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.name = rs.getString("name");
                u.email = rs.getString("email");
                return u;
            }
            throw new RuntimeException("User not found");

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user", e);
        }
    }
}
