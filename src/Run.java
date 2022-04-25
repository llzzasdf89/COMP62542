
public class Run {
    public static void main(String[] args) {

        Student student = new Student(11);
        student.state();
        student.state();
        student.state();
        /**
         * Since Course has been modified to abstract class，
         * so the instance turns to use ManCourse or OptCourse
         */
        Course course1 = new ManCourse("aaa", "se1", null);
        // Course course2 = new OptCourse("bbb", "se2", null, null);
        // Test for Factory pattern
        Course course2 = CourseFactory.getCourse("optcourse", "bbb", "se2", null, null);
        Student student1 = new Student(222);
        student1.state();
        student1.state();
        student1.state();
        // Test for Factory pattern
        Student student2 = StudentFactory.getStudent(333);
        student2.state();
        student2.state();

        student.addCourse(course1);
        student.addCourse(course2);

        System.out.println("s1   " + student.getCourse());

        student1.addCourse(course1);
        System.out.println("s2   " + student1.getCourse());

        Newsletter newsletter1 = new Newsletter("n1");
        Newsletter newsletter2 = new Newsletter("n2");

        student.subscribeNewsletter(newsletter1);
        student.subscribeNewsletter(newsletter2);

        System.out.println("s1   " + student.getNewsletters());

        student1.subscribeNewsletter(newsletter2);
        System.out.println("2   " + student1.getNewsletters());

        StudentUnion studentUnion = new StudentUnion();
        studentUnion.updateNewsletter(newsletter1, "n111");

        // Test for Singleton pattern
        StudentAdmissionsOffice studentAdmissionOffice = StudentAdmissionsOffice.getInstance();
        studentAdmissionOffice.sendReminder(student, "r1");

        StudentSupportOffice studentSupportOffice = new StudentSupportOffice(StudentAdmissionsOffice.getInstance());
        studentSupportOffice.addCourse(student1, course2);
        System.out.println("s2   " + student1.getCourse());
        // Test for Visitor Pattern
        System.out.println();
        System.out.println("Student with fully registerd Status");
        student1.visitManCourse(course1);// Student try to access ManCourse. Failure
        student1.visitOptCourse(course2);// Student try to access OptCourse, Success
        student1.setStudentState(new NotRegisteredState());
        System.out.println("After change the status to unregistered");
        student1.visitManCourse(course1);// Student try to access ManCourse. Failure
        student1.visitOptCourse(course2);// Student try to access OptCourse, Success
        System.out.println("After change the status to registered");
        student1.setStudentState(new RegisteredState());
        studentAdmissionOffice.visitManCourse(course1);// AdmissionOffice access the Mancourse, failure
        studentAdmissionOffice.visitOptCourse(course2); // AdmissionOffice access the Optcourse, failure
        studentSupportOffice.addStudent(student1);
        studentSupportOffice.visitManCourse(course1);// studentSupportOffice access the Mancourse, success
        studentSupportOffice.visitOptCourse(course2);// studentSupportOffice access the Optcourse, success

    }
}
