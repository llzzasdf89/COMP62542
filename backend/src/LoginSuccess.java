public class LoginSuccess extends LoginTemplate{

    @Override
    void loginResult(long uniNum) {
        System.out.println("Login success.");
        System.out.println("Welcome, student with uniNum " + uniNum);
        /**
         * Then follows the business logic of the status of the student.
         * However, because we do not determine the way of storing the students list
         * So I did not implement the following logic.
         */
    }
    
}
