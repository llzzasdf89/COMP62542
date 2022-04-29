
import java.util.ArrayList;

public class Student implements CourseVisitor {

    private long uniNum;

    private StudentState studentState;

    private ArrayList<Course> courses = new ArrayList<Course>();

    private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    private ArrayList<String> reminders = new ArrayList<String>();

    private SelectCourseStrategy selectCourseStrategy;

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

    public void setSelectCourseStrategy(SelectCourseStrategy selectCourseStrategy) {
        this.selectCourseStrategy = selectCourseStrategy;
    }
    public void selectCourse(Course course) {
        //select course through visitor and strategy pattern combination
        if (course instanceof ManCourse) this.visitManCourse(course);
        else this.visitOptCourse(course);
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