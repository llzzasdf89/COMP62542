package Entity;
public class PendingState implements StudentState {
    Student student;
    String studentState = "registration pending";

    @Override
    public void state() {
        System.out.println("You are now in pending!");
        pay();
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public Student getStudent() {
        return this.student;
    }

    public void pay() {
        System.out.println("you are done paying!");
        this.getStudent().setStudentState(new RegisteredState());

    }
}
