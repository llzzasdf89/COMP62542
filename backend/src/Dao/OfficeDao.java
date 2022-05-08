package Dao;
import java.sql.*;
import java.util.ArrayList;

import Entity.*;
public class OfficeDao {
    public ArrayList<Student> getAllPendingStudents() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Student> students = new ArrayList<>();
        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from students where studentState = 'registration pending'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long uniNum = rs.getLong("uniNum");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String courseType = rs.getString("courseType");
                String time = rs.getString("time");

                Student student = new Student(uniNum,name);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        System.out.println(students);
        return students;
    }
}
