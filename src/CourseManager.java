public interface CourseManager{
    /**
    *  Target Interface in Adpater Pattern.
    *  This target interface is used to add Course or remove Course
    */
    abstract void addCourse(Course course);
    abstract void removeCourse(Course course);
}