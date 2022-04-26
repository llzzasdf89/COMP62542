
public class ManCourse extends Course {
    public ManCourse(String courseNum, String name, Course.Time time) {
        super(courseNum, name, time);
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
