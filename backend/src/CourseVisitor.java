/**
 * Visitor Interface, playing the role of 'Visitor' mentioned in 'VisitorPatternLog'.
 */
public interface CourseVisitor{
    abstract void visitManCourse(Course ManCourse);
    abstract void visitOptCourse(Course OptCourse);
}