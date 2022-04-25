import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentSupportOffice extends OfficeDecorator implements CourseVisitor {
    private static StudentSupportOffice instance;

    public static synchronized StudentSupportOffice createInstance() {
        if (instance == null) {
            StudentAdmissionsOffice stu = StudentAdmissionsOffice.createInstance();
            instance = new StudentSupportOffice(stu);
        }

        return instance;
    }

    private ArrayList<Student> students = new ArrayList<Student>();

    public StudentSupportOffice(Office decoratedOffice) {

        super(decoratedOffice);
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public void sendReminder(Student student, String reminder) {
        student.reminderNotice(reminder);

    }

    @Override
    public void removeCourse(Student student, Course course) {
        student.removeCourse(course);
        course.removeStudent(student);

    }

    @Override
    public void addCourse(Student student, Course course) {

        student.addCourse(course);
        course.addStudent(student);
    }

    @Override
    public void visitManCourse(Course ManCourse) {
        /**
         * Student Support Office have the right to access both ManCourse and OptCourse
         * The difference is that Office could access all of the students in
         * fully-registed Status;
         * Therefore the businesslogic in this visit method is:
         * 1.find all the registed students
         * 2.find all the ManCourse they have.
         * 3.According to the input, decide to add or remove ManCourse of a student.(Not
         * implemented yet)
         */
        System.out.println("Course Accessed by Student Support Office");
        ArrayList<ManCourse> ManCourseList = new ArrayList<ManCourse>();
        Map<Long, ArrayList> map = new HashMap();
        for (Student s : students) {// access all the students
            if (s.getStudentState() instanceof RegisteredState) {
                // access the student's state, find the ones with Registered
                ArrayList<Course> courses = s.getCourse();
                for (Course course : courses) {// find the ManCourses of the student
                    if (course instanceof ManCourse)
                        ManCourseList.add((ManCourse) course);
                }
                System.out
                        .println("Student with unique number : " + s.getUniNum() + " has ManCourses: " + ManCourseList);
                map.put(s.getUniNum(), ManCourseList);
                ManCourseList.clear(); // clear the ManCourse List, waiting for the next student
            }
        }
        /**
         * The following logic is get the input from user, to determine delete or add
         * someone's manCourse
         * This is to be continued;
         */

    }

    @Override
    public void visitOptCourse(Course OptCourse) {
        /**
         * Student Support Office have the right to access both ManCourse and OptCourse
         * The difference is that Office could access all of the students in
         * fully-registed Status;
         * Therefore the businesslogic in this visit method is:
         * 1.find all the registed students
         * 2.find all the OptCourse they have.
         * 3.According to the input, decide to add or remove OptCourse of a student.(Not
         * implemented yet)
         */
        System.out.println("Course Accessed by Student Support Office");
        ArrayList<OptCourse> OptCourseList = new ArrayList<OptCourse>();
        Map<Long, ArrayList> map = new HashMap();
        for (Student s : students) {// access all the students

            if (s.getStudentState() instanceof RegisteredState) {
                // access the student's state, find the ones with Registered
                ArrayList<Course> courses = s.getCourse();
                for (Course course : courses) {// find the OptCourses of the student
                    if (course instanceof OptCourse)
                        OptCourseList.add((OptCourse) course);
                }
                System.out
                        .println("Student with unique number : " + s.getUniNum() + " has OptCourses: " + OptCourseList);
                map.put(s.getUniNum(), OptCourseList);
                OptCourseList.clear(); // clear the OptCourse List of current student, waiting for the next student
            }
        }
        /**
         * The following logic is get the input from user, to determine delete or add
         * someone's manCourse
         * This is to be continued;
         */
    }

}
