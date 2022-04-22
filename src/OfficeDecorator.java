//package StudentSys;

public abstract class OfficeDecorator implements Office {

    protected Office decoratorOffice;

    public OfficeDecorator(Office decoratorOffice) {

        this.decoratorOffice = decoratorOffice;

    }

    @Override
    public void addStudent(Student student) {

        decoratorOffice.addStudent(student);

    }

    @Override
    public void removeStudent(Student student) {

        decoratorOffice.removeStudent(student);
    }

    @Override
    public void sendReminder(Student student, String reminder) {

        decoratorOffice.sendReminder(student, reminder);

    }

    public void removeCourse(Student student, Course course) {
        student.removeCourse(course);
        course.removeStudent(student);

    }

    public void addCourse(Student student, Course course) {

        student.addCourse(course);
        course.addStudent(student);
    }
}
