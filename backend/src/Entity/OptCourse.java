package Entity;
public class OptCourse extends Course {
    private String department;

    public OptCourse(String courseNum, String name, String department, String courseType, String time) {
        super(courseNum, name, courseType, time);
        this.department = department;
    }
    public OptCourse(){}

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    void initateCourse() {//'template' method in the template pattern
        System.out.println("Beginning initating Optional Course");
        String output = String.format("Course number:%s\nCourse Name:%s",super.getCourseNum(),super.getName());
        System.out.println(output);
        System.out.println("Initiation finished");
    }
    @Override
    void accept(CourseVisitor Visitor) {//'accept' method in the visitor pattern
        /**
         * As the element to be accessed by Visitor,
         * the major aim of Visitor is to invocate the VisitXXXElement method,
         * passing self as parameter, the remain bussiness logic,including judge
         * instance of Element, will all be done by Visitor.
         */
        Visitor.visitOptCourse(this);
    }
}
