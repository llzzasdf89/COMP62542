import java.util.ArrayList;

public class StudentSupportOffice extends OfficeDecorator implements StudentVisitor {
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
        course.removeStudent(student);

    }

    @Override
    public void addCourse(Student student, Course course) {
        course.addStudent(student);
    }

    @Override
    public void visitStudent(Student student,Course course, String request) {
        /**
         * According to the accept from client, 
         *  decide to remove or add the course to the student;
         * 
         */
        if(request == "add") student.getCourse().add(course);
        else if (request == "delete") student.getCourse().remove(course);
        
    }

    

}
