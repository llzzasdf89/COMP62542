package Entity;
//import Course.Time;
public class CourseFactory {

    public static Course createCourse(String courseType, String courseNum, String name, String department,
            String day,String startTime,String endTime) {
        if (courseType == null)
            return null;
        if (courseType.equalsIgnoreCase("ManCourse"))
            return new ManCourse(courseNum, name, courseType, day,startTime,endTime);
        else if (courseType.equalsIgnoreCase("OptCourse"))
            return new OptCourse(courseNum, name, department, courseType, day,startTime,endTime);
        return null;
    }
}
