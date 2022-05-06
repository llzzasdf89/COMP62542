package Entity;

import Dao.CourseDao;

import java.util.ArrayList;

public class ManCourse extends Course {
    private ArrayList<Course> subActivities = new ArrayList<Course>();
    public void addSubActivity(Course course) {
        subActivities.add(course);
    }

    public void removeSubActivity(Course course) {
        subActivities.remove(course);
    }

    public ManCourse(String courseNum, String name, String courseType, String time) {
        super(courseNum, name, courseType, time);
        CourseDao courseDao  =new CourseDao();
        courseDao.addCourse(this);
    }

    
    @Override
    void initateCourse() {
        System.out.println("Beginning initating Mandatory Course");
        String output = String.format("Course number:%s\nCourse Name:%s",super.getCourseNum(),super.getName());
        System.out.println(output);
        System.out.println("Initiation finished");
    }

    @Override
    void accept(CourseVisitor Visitor) {
        /**
         * As the element to be accessed by Visitor,
         * the major aim of Visitor is to invocate the VisitXXXElement method,
         * passing self as parameter, the remain bussiness logic,including judge
         * instance of Element, will all be done by Visitor.
         */
        Visitor.visitManCourse(this);
    }

}
