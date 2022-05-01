
public class StudentAdmissionsOffice implements Office, StudentVisitor {
    StudentUnionAdapter studentUnionAdapter;
    private static StudentAdmissionsOffice instance;

    private StudentAdmissionsOffice() {
    }

    public static synchronized StudentAdmissionsOffice createInstance() {
        if (instance == null)
            instance = new StudentAdmissionsOffice();
        return instance;
    }

    @Override
    public void addStudent(Student student) {
        Data data = Data.getDataInstance();
        data.getStudentIterator().add(student); // use iterator pattern to add a new student to the list
    }

    @Override
    public void removeStudent(Student student) {
        Data data = Data.getDataInstance();
        data.getStudentIterator().add(student); // use iterator pattern to add a new student to the list
    }

    @Override
    public void sendReminder(String reminder) {
        Data data = Data.getDataInstance();
        DataIterator studentlist = data.getStudentIterator();
        while (studentlist.hasNext()) { // Find all the students which are registed. Send them reminder
            Student student = (Student) studentlist.next();
            if (student.getStudentState() instanceof RegisteredState)
                student.reminderNotice(reminder);
        }
    }

    @Override
    public void visitStudent(Student student, Course course, String request) {
        System.out.println("Admission Office is not able to visit student's courses");
    }

    @Override
    public void deleteNewsletter(Newsletter newsletter) {
        studentUnionAdapter = new StudentUnionAdapter();
        studentUnionAdapter.deleteNewsletter(newsletter);
    }

}
