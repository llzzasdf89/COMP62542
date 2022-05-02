package Dao;
import Entity.Newsletter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
