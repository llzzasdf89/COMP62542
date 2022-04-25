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
    void initateCourse() {
        System.out.println("Beginning initating Optional Course");
        String output = String.format("Course number:%s\nCourse Name:%s\nCourse Department:%s",super.getCourseNum(),super.getName(),super.getDepartment());
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
        Visitor.visitOptCourse(this);
    }
}
