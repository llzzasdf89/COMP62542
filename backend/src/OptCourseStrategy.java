public class OptCourseStrategy implements SelectCourseStrategy {
    @Override
    public void executeStrategy(Student student, Course course) {
        /**
         * State is registered and course belongs to OptCourse, let the Support Office
         * to add course for him
         */
        if(!(course instanceof OptCourse)) {
            System.out.println("You are not able to select Mandatory courses");
            return;
        }
        if (!(student.getStudentState() instanceof RegisteredState)) {
            System.out.println("You are not able to select course because you are not Registered");
            return;
        }
        /**
         * The follow logic is to accept the input from client, decide to remove course or add course
         * Remember: add course or remove course is actually done by Admission Office
         * So we need to use visitor pattern to call the Admission office to do so.
         */
        String request = "add";
        //call the office to do so;
        student.accept(StudentAdmissionsOffice.createInstance(), course, request);
    }

}
