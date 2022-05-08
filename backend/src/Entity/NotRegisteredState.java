package Entity;

import Dao.StudentDao;

public class NotRegisteredState implements StudentState {

    Student student;

    @Override
    public void state() {

        registration();
    }

    public void registration() {
        StudentDao studentDao = new StudentDao();
        studentDao.changeStatus(this.student.getUniNum(),"pending");
        this.getStudent().setStudentState(new PendingState());
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
