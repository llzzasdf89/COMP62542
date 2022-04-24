
import java.util.ArrayList;

public class StudentAdmissionsOffice implements Office,CourseVisitor {
    private ArrayList<Student> students = new ArrayList<Student>();

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
    public void visitManCourse(Course ManCourse) {
        /**
         * Student AdmissionOffice does not have right to add Course or remove Course from a student
         * Therefore, in the visit method, we directly return
         */
        System.out.println("Student Admission Office could not access students Course");
        return;
    }

    @Override
    public void visitOptCourse(Course OptCourse) {
        /**
         * Student AdmissionOffice does not have right to add Course or remove Course from a student
         * Therefore, in the visit method, we directly return
         */
        System.out.println("Student Admission Office could not access students Course");
        return;
    }
    
}
