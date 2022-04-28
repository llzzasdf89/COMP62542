
public class ManCourseStrategy implements SelectCourseStrategy {
    @Override
    public void selectCourse(Course course) {
        // When the course selected by student is Optional Course
        if (!(course instanceof OptCourse)) {
            System.out.println("You can not select Mandatory Courses");
            return;
        }
    }
}