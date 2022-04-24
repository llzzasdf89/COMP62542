
public class ManCourse extends Course {
    public ManCourse(String courseNum, String name, String department, Time time) {
        super(courseNum, name, department, time);
    }

    @Override
    void accept(CourseVisitor Visitor) {
        /**
         * As the element to be accessed by Visitor, 
         * the major aim of Visitor is to invocate the VisitXXXElement method, 
         * passing self as parameter, the remain bussiness logic,including judge instance of Element,  will all be done by Visitor.
         */
        Visitor.visitManCourse(this);
    }
    
}
