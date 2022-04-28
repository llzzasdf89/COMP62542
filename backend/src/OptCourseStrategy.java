public class OptCourseStrategy implements SelectCourseStrategy {
    Student student;

    public void accept(StudentVisitor visitor, Course course, String request) {
        visitor.visitStudent(student, course, request);
    }

    @Override
    public void selectCourse(Course course) {
        /**
         * State is registered and course belongs to OptCourse, let the Support Office
         * to add course for him
         */
        this.accept(StudentSupportOffice.createInstance(), course, "add");
    }

}
