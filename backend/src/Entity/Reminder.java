package Entity;

import java.util.ArrayList;

public class Reminder {
    public static ArrayList<Reminder> reminders = new ArrayList<>();
    private String content;
    private String id;
    private ArrayList<Student> subscribers = new ArrayList<Student>();

    public Reminder(String id, String content) {
        this.content = content;
        this.id = id;
        reminders.add(this);
    }

    public void setContent(String content){
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }
}
