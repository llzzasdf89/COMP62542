package Dao;

import Entity.Course;
import Entity.OptCourse;

import java.sql.*;
import java.util.*;

public class CourseDao {
    //add subActivity like tutorials and others to the database
    public void addSubActivity(Course course)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into courses(courseNum,name,courseType,time) values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getCourseNum());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getCourseType());
            stmt.setString(4, String.valueOf(course.getTime()));
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("add successfully！");
    }

    //remove subActivity like tutorials and others to the database
    public void removeSubActivity(String removeCourseNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "delete from courses where courseNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, removeCourseNum);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("remove successfully!");
    }

    //show all optional courses in the database
    public ArrayList<Course> getAllOptionalCourses() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Course> subActivity = new ArrayList<>();

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from courses where courseType = 'OptCourse'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String courseNum = rs.getString("courseNum");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String courseType = rs.getString("courseType");
                String time = rs.getString("time");

                Course course = new OptCourse(courseNum,name,department,courseType,time);
                subActivity.add(course);
                System.out.println(course.getCourseNum());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        return subActivity;
    }
}
