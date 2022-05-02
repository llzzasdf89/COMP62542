package Entity;
public interface Office {
    public void addStudent(Student student);

    /**
     * All the operations including add student or delete student
     * will all be done through iterator of data.
     */
    public void removeStudent(Student student);

    public void sendReminder(String reminder);

    public void deleteNewsletter(Newsletter newsletter);
}
