
import java.util.ArrayList;

public class StudentAdmissionsOffice implements Office,StudentVisitor {
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
    public void visitStudent(Student student,Course course,String request) {
        System.out.println("Admission Office Visit Student");
    }

    
    
}
