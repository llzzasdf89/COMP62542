package Entity;
public class StudentUnionAdapter implements Office {

	StudentUnion studentUnion;
	
	public StudentUnionAdapter() {
		studentUnion= new StudentUnion();
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendReminder(String reminder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNewsletter(Newsletter newsletter) {
		studentUnion.deleteNewsletter(newsletter);
	}
}
