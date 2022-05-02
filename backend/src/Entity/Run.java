package Entity;

import Dao.CourseDao;
import Dao.NewsletterDao;
import Dao.StudentDao;

public class Run {
    public static void main(String[] args) {
        // Test for iterator pattern
        StudentSupportOffice stuSup = StudentSupportOffice.createInstance();
        Data data = Data.getDataInstance(); // Data is singleton class, through the method getDataInstance to get it.
        // Remember, data contains all the students and courses.
        DataIterator CourseIterator = data.getCourseIterator();
        DataIterator StudentIterator = data.getStudentIterator();
        Newsletter newsletter1 = new Newsletter("aa","1");
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
            student.selectCourse(new OptCourse("11", "OptCourse11", null, (Course.Time) null));
        }
        // Test for interaction with the database
        StudentDao stuDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        NewsletterDao newsDao = new NewsletterDao();
        ManCourse manCourse1 = new ManCourse("COM1","ManCourse1",null);
        OptCourse optCourse1 = new OptCourse("OPT1","OptCourse1","CS",(Course.Time)null);
        courseDao.addSubActivity(manCourse1);
        courseDao.addSubActivity(optCourse1);
        stuDao.addCourseSelection(new Student(001),manCourse1);
        stuDao.addCourseSelection(new Student(002),optCourse1);
        stuDao.findCourse(001);
        courseDao.getAllCourses();
        courseDao.removeSubActivity("COM1");
        courseDao.removeSubActivity("OPT1");
        stuDao.removeCourseSelection((long) 001,"COM1");
        stuDao.removeCourseSelection((long) 002,"OPT1");
        newsDao.addNewsletter(newsletter1);
        newsDao.updateNewsletter("1","hi");
        newsDao.removeNewsletter("1");
    }
}