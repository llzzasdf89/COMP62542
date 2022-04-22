//package StudentSys;

import java.util.ArrayList;

public class Course {
    private String courseNum;
    private String name;
    private String department;

    enum Time {
        Mon, Tue, Wed, Thu, Fri
    }

    private Time time;
    private ArrayList<Course> subActivities = new ArrayList<Course>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public Course(String courseNum, String name, String department, Time time) {
        this.courseNum = courseNum;
        this.name = name;
        this.department = department;
        this.time = time;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addSubActivity(Course course) {
        subActivities.add(course);
    }

    public void removeSubActivity(Course course) {
        subActivities.remove(course);
    }

    public ArrayList<Course> getSubActivities() {
        return this.subActivities;
    }

}