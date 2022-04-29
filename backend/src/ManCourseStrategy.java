public class ManCourseStrategy implements SelectCourseStrategy {
    @Override
    public void executeStrategy(Course course) {
        System.out.println("You can not select Mandatory Courses");
        return;
    }
}