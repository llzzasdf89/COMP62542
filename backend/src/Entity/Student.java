package Entity;
import Dao.StudentDao;

import java.util.ArrayList;

public class Student implements CourseVisitor {
    public static ArrayList<Student> students = new ArrayList<>();

    private long uniNum;

    private String name;

    private StudentState studentState;

    private ArrayList<Course> courses = new ArrayList<Course>();

    private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    public ArrayList<Reminder> reminders = new ArrayList<Reminder>();

    private SelectCourseStrategy selectCourseStrategy;

    public void setUniNum(long uniNum) {
        this.uniNum = uniNum;
    }

    public long getUniNum() {
        return uniNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Student(long uniNum, String name) {
        this.uniNum = uniNum;
        this.name = name;
        StudentState studentState = new NotRegisteredState();
        this.setStudentState(studentState);
        StudentDao studentDao = new StudentDao();
        studentDao.newStudent(uniNum,name);
        students.add(this);
    }

    public ArrayList<Reminder> getReminders() {
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

    public void setSelectCourseStrategy(SelectCourseStrategy selectCourseStrategy) {
        this.selectCourseStrategy = selectCourseStrategy;
    }
    public void selectCourse(Course course) {
        //select course through visitor and strategy pattern combination
        if (course instanceof ManCourse) this.visitManCourse(course);
        else this.visitOptCourse(course);
    }

    public void subscribeNewsletter(Newsletter newsletter) {
        StudentDao studentDao = new StudentDao();
        studentDao.addNewsSelection(this.getUniNum(),newsletter.getNewsNum());
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
        StudentDao studentDao = new StudentDao();
        studentDao.removeNewsSelection(this.getUniNum(),newsletter.getNewsNum());
        newsletters.remove(newsletter);
        newsletter.removeSubscriber(this);
    }

    public void newsLetterUpdata() {
        System.out.println("newletter updated   ");
    }

    public void reminderNotice(Reminder reminder) {
       reminders.add(reminder);
    }

    @Override
    public void visitManCourse(Course ManCourse) {
        this.setSelectCourseStrategy(new ManCourseStrategy());
        this.selectCourseStrategy.executeStrategy(this, ManCourse);
    }

    @Override
    public void visitOptCourse(Course OptCourse) {
        this.setSelectCourseStrategy(new OptCourseStrategy());
        this.selectCourseStrategy.executeStrategy(this, OptCourse);
    }

    public void accept(StudentVisitor visitor, Course course, String request) {
    visitor.visitStudent(this, course, request);
    }
}