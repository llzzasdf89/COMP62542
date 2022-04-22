//package StudentSys;

public class Run {
    public static void main(String[] args) {

        Student student = new Student(11);
        student.state();
        student.state();
        student.state();
        Course course1 = new Course("aaa", "se1", null, null);
        Course course2 = new Course("bbb", "se2", null, null);
        Student student1 = new Student(222);
        student1.state();
        student1.state();
        student1.state();

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

        Office office = new StudentAdmissionsOffice();
        office.sendReminder(student, "r1");

        OfficeDecorator officeDecorator = new StudentSupportOffice(new StudentAdmissionsOffice());

        officeDecorator.addCourse(student1, course2);
        System.out.println("s2   " + student1.getCourse());

    }
}
