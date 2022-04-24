public class LoginFail extends LoginTemplate{

    @Override
    void loginResult(long uniNum) {
        System.out.println("Login failed, Please check the unique Number again");
    }
    
}
