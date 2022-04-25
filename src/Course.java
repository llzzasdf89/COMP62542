import java.util.ArrayList;

/**
 * After adding Visitor Pattern, Course needs to be modified to abstract
 * classes;
 * This is because it needs to be abstracted as 'Element', and then define the
 * common method of Element, 'accept'
 * As for related concept about Visitor Pattern, please refer the document
 * 'VisitorPatternLog'
 */
public abstract class Course {
    private String courseNum;
    private String name;

    enum Time {
        Mon, Tue, Wed, Thu, Fri
    }

    private Course.Time time;
    private ArrayList<Course> subActivities = new ArrayList<Course>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public Course(String courseNum, String name, Course.Time time) {
        this.courseNum = courseNum;
        this.name = name;
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

    abstract void accept(CourseVisitor Visitor);
}
