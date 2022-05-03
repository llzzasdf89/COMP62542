package Entity;
//import Course.Time;
public class CourseFactory {

    public static Course createCourse(String courseType, String courseNum, String name, String department,
            String time) {
        if (courseType == null)
            return null;
        if (courseType.equalsIgnoreCase("ManCourse"))
            return new ManCourse(courseNum, name, courseType, time);
        else if (courseType.equalsIgnoreCase("OptCourse"))
            return new OptCourse(courseNum, name, department, courseType, time);
        return null;
    }
}
