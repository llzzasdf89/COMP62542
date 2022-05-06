package Dao;

import Entity.Course;
import Entity.OptCourse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.*;

public class CourseDao {
    //add subActivity like tutorials and others to the database
    public void addCourse(Course course)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into courses(courseNum,name,courseType,day) values(?,?,?,?)";
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
    public void removeCourse(String removeCourseNum) {
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
    public JSONArray getAllCourseJson() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JSONArray jsonArray = new JSONArray();

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from courses";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                StudentDao studentDao =new StudentDao();
                JSONObject courseJSon = new JSONObject();
                courseJSon.element("courseNum",rs.getString("courseNum"));
                courseJSon.element("name",rs.getString("name"));
                courseJSon.element("department",rs.getString("department"));
                courseJSon.element("courseType",rs.getString("courseType"));
                courseJSon.element("day",rs.getString("day"));
                courseJSon.element("startTime",rs.getString("startTime"));
                courseJSon.element("endTime",rs.getString("endTime"));
                courseJSon.element("subActivities",studentDao.getSubActivites(rs.getString("courseNum")));
                jsonArray.add(courseJSon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
}
