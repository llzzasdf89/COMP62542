package Entity;

import java.util.ArrayList;

/**
 * After adding Visitor Pattern, Course needs to be modified to abstract classes;
 * This is because it needs to be abstracted as 'Element', and then define the common method of Element, 'accept'
 * As for related concept about Visitor Pattern, please refer the document 'VisitorPatternLog'
 * 
 * 
 * Course is also a 'template' for ManCourse and OptCourse;
 * In this template, the template method is initateCourse, Mancourse and OptCourse needs to override it
 */
public abstract class Course {
    private String courseNum;
    private String name;
    public String time;
    private String courseType;
    private ArrayList<Course> subActivities = new ArrayList<Course>();
    private ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Course> courses = new ArrayList<>();

    public Course(String courseNum, String name, String courseType, String time) {
        this.courseNum = courseNum;
        this.name = name;
        this.time = time;
        this.courseType = courseType;
        courses.add(this);
    }
    public Course(){}
    abstract void initateCourse(); //template method, needs to be override by ManCourse and OptCourse
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

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    public String getCourseType() {
        return courseType;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
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

    abstract void accept(CourseVisitor Visitor);
}
