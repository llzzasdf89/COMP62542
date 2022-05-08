package Entity;
public class StudentFactory {
    public static Student createStudent(long uniNum, String name) {
        return new Student(uniNum,name);
    }
}
