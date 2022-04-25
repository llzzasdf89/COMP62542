public class StudentFactory {
    public static Student getStudent(long uniNum) {
        return new Student(uniNum);
    }
}
