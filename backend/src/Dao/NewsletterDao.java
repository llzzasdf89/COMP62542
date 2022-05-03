package Dao;
import Entity.Course;
import Entity.Newsletter;
import Entity.OptCourse;

import java.sql.*;
import java.util.ArrayList;

public class NewsletterDao {
    //add newsletter to the database
    public void addNewsletter(Newsletter newsletter)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into newsletter(content,newsNum) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newsletter.getContent());
            stmt.setString(2, newsletter.getNewsNum());
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

    //remove newsletter to the database
    public void removeNewsletter(String removeNewsNum) {
        //remove selection results from the database
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "delete from newsletter where newsNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, removeNewsNum);
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

    //update the content of newsletter to the database
    public void updateNewsletter(String newsNum, String content) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "update newsletter set content = ? where newsNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, content);
            stmt.setString(2, newsNum);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("update successfully!"+content);
    }

    //show all newsletters in the database
    public ArrayList<Newsletter> getAllNewsletters() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Newsletter> newsletters = new ArrayList<>();

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from newsletter ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String content = rs.getString("content");
                String newsNum = rs.getString("newsNum");

                Newsletter newsletter = new Newsletter(content,newsNum);
                newsletters.add(newsletter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        System.out.println(newsletters);
        return newsletters;
    }
}
