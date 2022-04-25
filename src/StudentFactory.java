public class StudentFactory {
    public static Student createStudent(long uniNum) {
        return new Student(uniNum);
    }
}
