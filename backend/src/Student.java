
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

    public SelectCourseStrategy getSelectCourseStrategy() {
        return selectCourseStrategy;
    }

    // public void selectCourse(SelectCourseStrategy selectStrategy, Course course)
    // {
    // /**
    // * The overall logic of this function is
    // * 1.judge whether the course selected by student is Optional Course
    // * 2.Then we judge whether his state is registered.
    // * 3.If both satisfies, let the Support Office to add course for him.
    // */
    // if (!(course instanceof OptCourse)) {
    // System.out.println("You can not select Mandatory Courses");
    // return;
    // } else if (!(this.studentState instanceof RegisteredState)) {
    // System.out.println("You are not able to select course because you are not
    // registered");
    // return;
    // }
    // this.accept(StudentSupportOffice.createInstance(), course, "add");
    // }

    public void executeStrategy(Course course) {
        selectCourseStrategy.selectCourse(course);
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
        /**
         * Since students in any status are not able to access ManCourse,
         * therefore in this method we only output some warnings and do nothing at all.
         */
        System.out.println("Mandatory Course Accessed by Student");
        System.out.println("You are not able to access Mandatory Course");
    }

    @Override
    public void visitOptCourse(Course OptCourse) {
        this.setSelectCourseStrategy(new OptCourseStrategy());
        /**
         * Since students only in fully registered Status could access OptCourse,
         * and they could add OptCourse or Remove OptCourse.
         * Therefore, in this method, we need to judge the status first, and then add
         * course or remove course according to the request
         */
        System.out.println("Optional Course Accessed by Student");
        if (this.studentState instanceof NotRegisteredState || this.studentState instanceof PendingState) {
            System.out.println(
                    "Now you are currently in not Fully Registerd state, so you are not able to modify your Courses");
            return;
        } // if the student is not fully registered, directly return.
        /**
         * Else we just print out the optional Courses for the student to choose
         */
        ArrayList<OptCourse> OptCourseList = new ArrayList<OptCourse>();
        for (Course c : this.courses) {
            if (c instanceof OptCourse)
                OptCourseList.add((OptCourse) c);
        }
        System.out.println("Now your Optional Courses are : " + OptCourseList);
        // this.selectCourse(OptCourse);
        this.executeStrategy(OptCourse);
    }

    // public void accept(StudentVisitor visitor, Course course, String request) {
    // visitor.visitStudent(this, course, request);
    // }
}