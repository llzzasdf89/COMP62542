package StudentSys;

import java.util.ArrayList;

public class Course {
    private String courseNum;
    private ArrayList<Course> subActivities = new ArrayList<Course>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public Course(String courseNum){
        this.courseNum = courseNum;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void addSubActivity(Course course){
        subActivities.add(course);
    }

    public void removeSubActivity (Course course){
        subActivities.remove(course);
    }

    public ArrayList<Course> getSubActivities(){
        return this.subActivities;
    }


}
