//package StudentSys;

import java.util.ArrayList;

public class Student {

    private long uniNum;

    private StudentState studentState;

    private ArrayList<Course> courses = new ArrayList<Course>();

    private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    private ArrayList<String> reminders = new ArrayList<String>();

    public void setUniNum(long uniNum) {
        this.uniNum = uniNum;
    }

    public long getUniNum() {
        return uniNum;
    }

    public Student(long uniNum) {
        this.uniNum = uniNum;
        StudentState studentState = new NotRegisteredState();
        this.setStudentState(studentState);
    }

    public ArrayList<String> getReminders() {
        return reminders;
    }

    public void setStudentState(StudentState studentState) {
        this.studentState = studentState;
        studentState.setStudent(this);

    }

    public StudentState getStudentState() {
        return studentState;
    }

    public void state() {
        this.studentState.state();
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.removeStudent(this);
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.addStudent(this);
    }

    public void subscribeNewsletter(Newsletter newsletter) {
        newsletters.add(newsletter);
        newsletter.addSubscriber(this);

    }

    public ArrayList<Newsletter> getNewsletters() {
        return this.newsletters;
    }

    public ArrayList<Course> getCourse() {
        return this.courses;
    }

    public void cancelNewsletter(Newsletter newsletter) {
        newsletters.remove(newsletter);
        newsletter.removeSubscriber(this);
    }

    public void newsLetterUpdata() {
        System.out.println("newletter updated   ");
    }

    public void reminderNotice(String reminder) {
        System.out.println(reminder);

    }
}
