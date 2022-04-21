package StudentSys;

public class NotRegisteredState implements StudentState{

    Student student;
    @Override
    public void state() {
        System.out.println("You are not registered!");
        registration();

    }

    public void registration(){
        System.out.println("you are done registration!");
        this.getStudent().setStudentState(new PendingState());
    }

    @Override
    public void setStudent(Student student){
        this.student = student;
    }

    @Override
    public Student getStudent() {
        return this.student;
    }
}
