public class Context {
    private SelectCourseStrategy strategy;
    private StudentState studentState;

    public Context(SelectCourseStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Course course) {
        if (!(this.studentState instanceof RegisteredState)) {
            System.out.println("You are not able to select course because you are not registered");
            return;
        }
        strategy.selectCourse(course);
    }
}
