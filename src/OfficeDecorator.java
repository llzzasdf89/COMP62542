
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
    public void sendReminder(String reminder) {

        decoratorOffice.sendReminder(reminder);

    }

    public void removeCourse(Student student, Course course) {
        course.removeStudent(student);

    }

    public void addCourse(Student student, Course course) {
        course.addStudent(student);
    }
}
