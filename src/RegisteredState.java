package StudentSys;

import java.util.ArrayList;

public class RegisteredState implements StudentState{

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


}
