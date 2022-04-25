public class OptCourse extends Course {
    private String department;

    public OptCourse(String courseNum, String name, String department, Course.Time time) {
        super(courseNum, name, time);
        this.department = department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    void accept(CourseVisitor Visitor) {
        /**
         * As the element to be accessed by Visitor,
         * the major aim of Visitor is to invocate the VisitXXXElement method,
         * passing self as parameter, the remain bussiness logic,including judge
         * instance of Element, will all be done by Visitor.
         */
        Visitor.visitOptCourse(this);
    }
}
