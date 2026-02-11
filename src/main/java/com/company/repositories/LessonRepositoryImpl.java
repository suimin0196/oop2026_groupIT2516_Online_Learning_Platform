package com.company.repositories;

import com.company.db.IDB;
import com.company.db.JdbcHelper;
import com.company.db.RowMapper;
import com.company.models.Lesson;

import java.util.List;

public class LessonRepositoryImpl implements LessonRepository {

    private final JdbcHelper jdbc;

    public LessonRepositoryImpl(IDB db) {
        this.jdbc = new JdbcHelper(db);
    }

    private final RowMapper<Lesson> mapper = rs -> {
        Lesson l = new Lesson();
        l.id = rs.getInt("id");
        l.courseId = rs.getInt("course_id");
        l.title = rs.getString("title");
        return l;
    };

    @Override
    public void create(Lesson lesson) {
        String sql = "INSERT INTO lessons(course_id, title) VALUES (?, ?)";
        jdbc.update(sql, ps -> {
            ps.setInt(1, lesson.courseId);
            ps.setString(2, lesson.title);
        });
    }

    @Override
    public List<Lesson> findByCourseId(int courseId) {
        String sql = "SELECT * FROM lessons WHERE course_id = ?";
        return jdbc.query(sql, ps -> ps.setInt(1, courseId), mapper);
    }

    @Override
    public Lesson findById(Integer lessonId) {
        String sql = "SELECT * FROM lessons WHERE id = ?";
        return jdbc.queryForObject(sql, ps -> ps.setInt(1, lessonId), mapper);
    }

    @Override
    public boolean exists(Integer id) {
        String sql = "SELECT 1 FROM lessons WHERE id = ?";
        Integer found = jdbc.queryForObject(sql, ps -> ps.setInt(1, id), rs -> rs.getInt(1));
        return found != null;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM lessons WHERE id = ?";
        jdbc.update(sql, ps -> ps.setInt(1, id));
    }

    @Override
    public void update(Lesson lesson) {
        String sql = "UPDATE lessons SET course_id = ?, title = ? WHERE id = ?";
        jdbc.update(sql, ps -> {
            ps.setInt(1, lesson.courseId);
            ps.setString(2, lesson.title);
            ps.setInt(3, lesson.id);
        });
    }

    @Override
    public List<Lesson> findAll() {
        String sql = "SELECT * FROM lessons";
        return jdbc.query(sql, null, mapper);
    }
}
