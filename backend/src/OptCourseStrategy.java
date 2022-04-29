public class OptCourseStrategy implements SelectCourseStrategy {
    Student student;
    StudentState studentState;

    public void accept(StudentVisitor visitor, Course course, String request) {
        visitor.visitStudent(student, course, request);
    }

    @Override
    public void executeStrategy(Course course) {
        /**
         * State is registered and course belongs to OptCourse, let the Support Office
         * to add course for him
         */
        if (!(this.studentState instanceof RegisteredState)) {
            System.out.println("You are not able to select course because you are not registered");
            return;
        }
        this.accept(StudentSupportOffice.createInstance(), course, "add");
    }

}
