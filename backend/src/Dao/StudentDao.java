package Dao;
import java.sql.*;

import Entity.*;
public class StudentDao {
    //add courses selection results to the database
    public void addCourseSelection(Student student, Course course)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into courseselection(uniNum,courseNum) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, student.getUniNum());
            stmt.setString(2, course.getCourseNum());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("add courses successfully！");
    }

    //remove courses selection results from the database
    public void removeCourseSelection(Long removeUniNum, String removeCourseNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "delete from courseselection where uniNum = ? and courseNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, removeUniNum);
            stmt.setString(2, removeCourseNum);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("remove courses successfully!");
    }

    //find courses selection results from the database according to the UniNum
    public Course findCourse(int findUniNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Course course = null;
        try{
            conn = JDBCUtil.getConn();
            String sql = "select * from courseselection where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, findUniNum);
            ResultSet tmpres = stmt.executeQuery();
            course = new OptCourse();
            while(tmpres.next()){
                course.setCourseNum(tmpres.getString("courseNum"));
            }
            System.out.println("find courses");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println(course.getCourseNum());
        return course;
    }

    //add newsletter selection results to the database
    public void addNewsSelection(Student student, Newsletter newsletter)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into newsletterselection(uniNum,newsNum) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, student.getUniNum());
            stmt.setString(2, newsletter.getNewsNum());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("add newsletter successfully！");
    }

    //remove newsletter selection results from the database
    public void removeNewsSelection(Long removeUniNum, String removeNewsNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "delete from newsletterselection where uniNum = ? and newsNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, removeUniNum);
            stmt.setString(2, removeNewsNum);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("remove newsletter successfully!");
    }

    //find newsletter subscribe results from the database according to the UniNum
    public Newsletter findNewsletter(int findUniNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Newsletter newsletter = null;
        try{
            conn = JDBCUtil.getConn();
            String sql = "select * from newsletterselection where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, findUniNum);
            ResultSet tmpres = stmt.executeQuery();
            newsletter = new Newsletter();
            while(tmpres.next()){
                newsletter.setNewsNum(tmpres.getString("newsNum"));
            }
            System.out.println("find newsletter");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println(newsletter.getNewsNum());
        return newsletter;
    }
}
