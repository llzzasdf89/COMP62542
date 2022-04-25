//import Course.Time;
public class CourseFactory {

    public static Course createCourse(String courseType, String courseNum, String name, String department,
            Course.Time time) {
        if (courseType == null)
            return null;
        if (courseType.equalsIgnoreCase("ManCourse"))
            return new ManCourse(courseNum, name, time);
        else if (courseType.equalsIgnoreCase("OptCourse"))
            return new OptCourse(courseNum, name, department, time);
        return null;
    }
}
