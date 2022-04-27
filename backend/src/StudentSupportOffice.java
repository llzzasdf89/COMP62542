
public class StudentSupportOffice extends OfficeDecorator implements StudentVisitor {
    private static StudentSupportOffice instance;

    public static synchronized StudentSupportOffice createInstance() {
        if (instance == null) {
            StudentAdmissionsOffice stu = StudentAdmissionsOffice.createInstance();
            instance = new StudentSupportOffice(stu);
        }

        return instance;
    }
    public StudentSupportOffice(Office decoratedOffice) {

        super(decoratedOffice);
    }

    @Override
    public void addStudent(Student student) {
        //Students support office is not able to add Students to the lists, so left blank here
    }

    @Override
    public void removeStudent(Student student) {
        //Students support office is not able to remove Students to the lists, so left blank here
    }

    @Override
    public void sendReminder(String reminder) {
        //Student Support Office is not able to send reminder, so left it blank
    }

    @Override
    public void removeCourse(Student student, Course course) {
        //The remove operation is to delete course from a student's course list and the student in the course list.
        student.getCourse().remove(course);
        course.removeStudent(student);
    }

    @Override
    public void addCourse(Student student, Course course) {
        //The add operation is to add course from a student's course list and the student in the course list.
        student.getCourse().add(course);
        course.addStudent(student);
    }

    @Override
    public void visitStudent(Student student,Course course, String request) {
        /**
         * Student Support Office is allowed to visit student to add or delete the course
         * This is the visit method in the Visitor Pattern. 
         */
        if(request == "add") this.addCourse(student, course);
        else if (request == "delete") this.removeCourse(student, course);
    }

    

}
