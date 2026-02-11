package com.company.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Lightweight helper to reduce JDBC boilerplate
public class JdbcHelper {
    private final IDB db;

    public JdbcHelper(IDB db) {
        this.db = db;
    }

    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> mapper) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (pss != null) pss.setValues(ps);
            try (ResultSet rs = ps.executeQuery()) {
                List<T> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(mapper.mapRow(rs));
                }
                return results;
            }
        } catch (SQLException e) {
            throw new RuntimeException("JDBC query error", e);
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> mapper) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (pss != null) pss.setValues(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("JDBC queryForObject error", e);
        }
    }

    public int update(String sql, PreparedStatementSetter pss) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (pss != null) pss.setValues(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("JDBC update error", e);
        }
    }
}
