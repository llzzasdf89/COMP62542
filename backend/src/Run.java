
public class Run {
    public static void main(String[] args) {
        // Test for iterator pattern
        StudentSupportOffice stuSup = StudentSupportOffice.createInstance();
        Data data = Data.getDataInstance(); // Data is singleton class, through the method getDataInstance to get it.
        // Remember, data contains all the students and courses.
        DataIterator CourseIterator = data.getCourseIterator();
        DataIterator StudentIterator = data.getStudentIterator();
        Newsletter newsletter1 = new Newsletter("aa");
        StudentUnion s = new StudentUnion();
        s.addNewsletter(newsletter1);
        stuSup.deleteNewsletter(newsletter1);
        while (StudentIterator.hasNext()) {
            Student student = (Student) StudentIterator.next();
            while (CourseIterator.hasNext()) {
                Course course = (Course) CourseIterator.next();
                if (course instanceof ManCourse)
                    stuSup.addCourse(student, course);
            }
            CourseIterator = data.getCourseIterator();
            System.out.println("Student id: " + student.getUniNum());
            System.out.println("Student Courses : " + student.getCourse());
            // Test for Strategy Pattern
            student.setSelectCourseStrategy(new ManCourseStrategy());
            student.selectCourse(new ManCourse("10", "ManCourse10", null));
            student.setSelectCourseStrategy(new OptCourseStrategy());
            student.selectCourse(new OptCourse("11", "OptCourse11", null, null));
        }
    }
}