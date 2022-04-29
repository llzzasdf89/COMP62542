
public class Run {
    public static void main(String[] args) {
        // Test for iterator pattern
        StudentSupportOffice stuSup = StudentSupportOffice.createInstance();
        Data data = Data.getDataInstance(); // Data is singleton class, through the method getDataInstance to get it.
        // Remember, data contains all the students and courses.
        DataIterator CourseIterator = data.getCourseIterator();
        DataIterator StudentIterator = data.getStudentIterator();
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
            Context context = new Context(new ManCourseStrategy());
            context.executeStrategy(new ManCourse("10", "ManCourse10", null));
        }
    }
}
