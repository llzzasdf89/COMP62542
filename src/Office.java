//package StudentSys;

public interface Office {
    public void addStudent(Student student);

    public void removeStudent(Student student);

    public void sendReminder(Student student, String reminder);
}
