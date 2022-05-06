package Entity;
import java.util.ArrayList;

public class RegisteredState implements StudentState {

    Student student;

    private ArrayList<Course> courses = new ArrayList<Course>();

    private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    @Override
    public void state() {
        System.out.println("you are registered!");
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public Student getStudent() {
        return this.student;
    }

    public void setCourse(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Course> getCourse() {
        return this.courses;
    }

    public void setNewsletter(ArrayList<Newsletter> newsletters) {
        this.newsletters = newsletters;
    }

    public ArrayList<Newsletter> getNewsletter() {
        return this.newsletters;
    }

}
