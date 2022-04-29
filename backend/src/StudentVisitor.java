public interface StudentVisitor {
    abstract void visitStudent(Student student,Course course,String request);
}
