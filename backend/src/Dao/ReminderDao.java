package Dao;

import Entity.Newsletter;
import Entity.Reminder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class ReminderDao {
    //add reminder to the database
    public void addReminder(Reminder reminder)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into reminder(content,id) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminder.getContent());
            stmt.setString(2, reminder.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeConn(conn, stmt);
        }
        System.out.println("add reminder successfully！");
    }

    public JSONArray getReminderJson(){
        JSONArray reminderInfoJson = new JSONArray();
        for (Reminder r: Reminder.reminders) {
            JSONObject reminder = new JSONObject();
            reminder.element("id",r.getId());
            reminder.element("content",r.getContent());
            reminderInfoJson.add(reminder);

        }
        return reminderInfoJson;
    }


    //remove newsletter to the database


    //update the content of newsletter to the database

}
