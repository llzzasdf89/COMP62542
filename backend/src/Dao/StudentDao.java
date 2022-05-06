package Dao;
import java.sql.*;
import java.util.ArrayList;

import Entity.*;
import Service.StudentService;
import Service.StudentServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class StudentDao {

    public void newStudent(long uniNum, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into students(uniNum,studentName,studentState) values(?,?,'notRegistered')";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt);
        }
    }


    //get student information
    public JSONObject getStudentInfo(Long uniNum)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        String name = null;
        String studentState = null;

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from students where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                uniNum = resultSet.getLong("uniNum");
                 name = resultSet.getString("studentName");
                 studentState = resultSet.getString("studentState");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        StudentServiceImpl studentService = new StudentServiceImpl();
        JSONObject studentInfo = new JSONObject();
        studentInfo.element("uniNum",uniNum);
        studentInfo.element("name",name);
        studentInfo.element("status",studentState);
        studentInfo.element("courses",studentService.showTimeTable(uniNum));
        return studentInfo;
    }

    public JSONArray getStudentJson(){

        JSONArray studentsInfoJson = new JSONArray();
        for (Student s: Student.students) {
            studentsInfoJson.add(getStudentInfo(s.getUniNum()));
        }
        return studentsInfoJson;
    }

    public JSONArray getSubActivites(String courseNum){
        Connection conn = null;
        PreparedStatement stmt = null;
        String name = null;
        String studentState = null;
        JSONArray jsonArray = new JSONArray();

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from courses where courseNum in (select activityNum from subactivities where courseNum = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseNum);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                JSONObject course = new JSONObject();
                course.element("name",resultSet.getString("name"));
                course.element("courseNum",resultSet.getString("courseNum"));
                course.element("department",resultSet.getString("department"));
                course.element("courseType",resultSet.getString("courseType"));
                course.element("day",resultSet.getString("day"));
                course.element("startTime",resultSet.getString("startTime"));
                course.element("endTime",resultSet.getString("endTime"));
                jsonArray.element(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("get subactivities!");
        return jsonArray;
    }

    //add courses selection results to the database
    public void addCourseSelection(Long uniNum, String courseNum)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert ignore into courseselection(uniNum,courseNum) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            stmt.setString(2, courseNum);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("select course successfully！");
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
        System.out.println("exit course successfully!");
    }

    //find courses selection results from the database according to the UniNum
    public ArrayList<Course> getCourseSelection(Long uniNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Course> subActivity = new ArrayList<>();

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from courseselection where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String courseNum = rs.getString("courseNum");
                String sql1 = "select * from courses where courseNum = ?";
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, courseNum);
                ResultSet rs1 = stmt1.executeQuery();
                while(rs1.next()) {
                    String name = rs1.getString("name");
                    String department = rs1.getString("department");
                    String courseType = rs1.getString("courseType");
                    String day = rs1.getString("day");
                    Course course = new OptCourse(courseNum, name, department, courseType, day);
                    subActivity.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        System.out.println("get selection information of courses!");
        return subActivity;
    }

    //add newsletter selection results to the database
    public void addNewsSelection(Long uniNum, String newsNum)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into newsletterselection(uniNum,newsNum) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            stmt.setString(2, newsNum);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }

        System.out.println("subscribe newsletter successfully！");
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

    }

    //find newsletter subscribe results from the database according to the UniNum
    public ArrayList<Newsletter> getNewsSelection(Long uniNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Newsletter> newsletters = new ArrayList<>();

        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from newsletterselection where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String newsNum = rs.getString("newsNum");
                String sql1 = "select * from newsletter where newsNum = ?";
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, newsNum);
                ResultSet rs1 = stmt1.executeQuery();
                while(rs1.next()) {
                    String content = rs1.getString("content");
                    Newsletter newsletter = new Newsletter(content,newsNum);
                    newsletters.add(newsletter);
                    System.out.println(newsletter.getNewsNum());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        return newsletters;
    }

    //check the status
    public String getState(Long findUniNum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String state = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "select studentState from students where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, findUniNum);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                state = resultSet.getString("studentState");
                System.out.println(state);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("check state");
        return state;
    }


    //change the registration status
    public void changeStatus(Long uniNum, String studentState) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success =false;
        try {
            conn = JDBCUtil.getConn();
            String sql = "update students set studentState = ? where uniNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentState);
            stmt.setLong(2, uniNum);
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("change state successfully!");

    }

    public boolean judgeExist(Long uniNum, String courseNum){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConn();
            String sql = "select count(*) as count from courseselection where uniNum = ? and courseNum = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uniNum);
            stmt.setString(2, courseNum);
            rs = stmt.executeQuery();
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt, rs);
        }
        if(count==0) return false;
        else return true;
    }
}
