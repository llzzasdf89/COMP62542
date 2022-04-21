package StudentSys;

import java.util.ArrayList;

public class StudentSupportOffice extends OfficeDecorator{
    private ArrayList<Student> students = new ArrayList<Student>();

    public StudentSupportOffice(Office decoratedOffice){

        super(decoratedOffice);
    }

    @Override
    public void addStudent(Student student){
        students.add(student);
    }

    @Override
    public void removeStudent(Student student){
        students.remove(student);
    }

    @Override
    public void sendReminder(Student student, String reminder) {
        student.reminderNotice(reminder);

    }

    public void removeCourse(Student student,Course course){
        student.removeCourse(course);
        course.removeStudent(student);

    }
    public void addCourse(Student student, Course course){

        student.addCourse(course);
        course.addStudent(student);
    }

}
