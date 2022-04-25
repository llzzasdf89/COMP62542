import java.util.ArrayList;

public class Data {
    /**
     * Mock data:
     * Students is the list of all the students;
     * Courses is the list of all the Courses in this system;
     */
    ArrayList<Student> Students = new ArrayList<Student>();
    ArrayList<Course> Courses= new ArrayList<Course>();
    public Data (){
        for(int i = 0; i < 10; i++){
            Students.add(new Student(i));      
            Courses.add(new ManCourse(String.valueOf(i),String.valueOf(i),String.valueOf(i),null));
        }
        System.out.println(Students);
        System.out.println(Courses);
    }
}
