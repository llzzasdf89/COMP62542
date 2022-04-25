
public class Run {
    public static void main(String[] args) {
        Data d = new Data();
        Student student = new Student(11);
        student.state();
        student.state();
        student.state();
        /**
         * Since Course has been modified to abstract class，
         * so the instance turns to use ManCourse or OptCourse
         */
        Course course1 = new ManCourse("aaa", "se1", "Mathemetics", null);
        course1.initateCourse();
        Course course2 = new OptCourse("bbb", "se2", "Computer Science", null);
        course2.initateCourse();
        Student student1 = new Student(222);
        student1.state();
        student1.state();
        student1.state();

        System.out.println("s1   " + student.getCourse());
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

        StudentAdmissionsOffice studentAdmissionOffice = new StudentAdmissionsOffice();
        studentAdmissionOffice.sendReminder(student, "r1");

        StudentSupportOffice studentSupportOffice = new StudentSupportOffice(new StudentAdmissionsOffice());
        studentSupportOffice.addCourse(student1, course2);
        System.out.println("s2   " + student1.getCourse());

    }
}
