
package com.company.repositories;

import com.company.db.IDB;
import java.sql.*;

public class ProgressRepositoryImpl implements ProgressRepository {
    private IDB db;
    public ProgressRepositoryImpl(IDB db){this.db=db;}

    public void markCompleted(int userId,int lessonId){
        String sql = "INSERT INTO progress(user_id, lesson_id, course_id, completed) VALUES (?, ?, (SELECT course_id FROM lessons WHERE id = ?), true)";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ps.setInt(3, lessonId);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new RuntimeException("Failed to mark progress: lesson not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error marking progress", e);
        }
    }

    public int getProgress(int userId,int courseId){
        try(Connection con=db.getConnection()){
            PreparedStatement ps=con.prepareStatement(
              "SELECT COUNT(*) FROM progress WHERE user_id=? AND course_id=?");
            ps.setInt(1,userId); ps.setInt(2,courseId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        }catch(Exception e){e.printStackTrace();}
        return 0;
    }
}
