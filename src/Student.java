
import java.util.ArrayList;

public class Student implements CourseVisitor{

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

    @Override
    public void visitManCourse(Course ManCourse) {
        /**
         * Since students in any status are not able to access ManCourse,
         * therefore in this method we only output some warnings and do nothing at all.
         */
        System.out.println("Course Accessed by Student Support Office");
        System.out.println("You are not able to access Mandatory Course");
    }

    @Override
    public void visitOptCourse(Course OptCourse) {
        /**
         * Since students only in fully registered Status could access OptCourse,
         * and they could add OptCourse or Remove OptCourse.
         * Therefore, in this method, we need to judge the status first, and then add course or remove course according to the request
         */
        System.out.println("Course Accessed by Student");
        if(this.studentState instanceof NotRegisteredState || this.studentState instanceof PendingState){
            System.out.println("Now you are currently in not Fully Registerd state, so you are not able to modify your Courses");
            return ;
        } //if the student is not fully registered, directly return.
        /**
         * Else we just print out the optional Courses for the student to choose
         */
        ArrayList <OptCourse> OptCourseList = new ArrayList<OptCourse>();
        for(Course c : this.courses){
            if(c instanceof OptCourse) OptCourseList.add((OptCourse)c);
        }
        System.out.println("Now your Optional Courses are : " + OptCourseList);
        /**
         * The remain business logic are 
         * 1.accept input from user
         * 2.Decide to add the course or remove the course..etc.
         * Since we did not determine the input way, either through command line or UI, so here I just comment them.
         * Scanner input = new Scanner(System.in);
         * if(input === 1 ) OptCourseList.add(OptCourse)
         * else (input === 2) OptCourseList.remove(OptCourse)
         * ...
         */
    }
}
