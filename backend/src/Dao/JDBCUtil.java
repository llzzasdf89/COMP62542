package Dao;

import java.sql.*;

public class JDBCUtil {

    private static String url = "jdbc:mysql://127.0.0.1:3306/student_system?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";  //url中的status为数据库库名
    private static String user = "root";
    private static String password = "";
    private static Connection con = null;

    //get connection
    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //close connection if there is a result
    public static void closeConn(Connection conn, Statement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    //close connection without results
    public static void closeConn(Connection conn, Statement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    //test for connection
    public static void main(String[] args) {
        System.out.print(getConn());
    }

}

