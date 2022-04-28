public class NotRegisteredStrategy implements SelectCourseStrategy {
    StudentState studentState;

    @Override
    public void selectCourse(Course course) {
        // When student's state is not registered
        if (!(this.studentState instanceof RegisteredState)) {
            System.out.println("You are not able to select course because you are not registered");
            return;
        }
    }
}
