public abstract class LoginTemplate {
    final public long acceptInput(){
        /**
         * step 1. accept input from frontend or command line
         * Notice: because how we accept input is not determined, 
         * so here I used a mock data ,which is 111;
         */
        return 111;
    }
    final public boolean isUniNumExist(long uniNum){
        /**
         * step 2.According to the uniNum, judge whether this number correspond to a student;
         */
        if(uniNum == 111) return true;
        return false;
    }
    abstract void loginResult(long input);//step 3, to be implemented by concrete template
    final public void Login(){
        /**
         * Template method, as introduced in 'TemplatePattern.pdf',
         * the functionality is to sequentially invocate the step 1 to step 3
         */
        long input = acceptInput();
        boolean isUniNumExist = isUniNumExist(input);
        if(isUniNumExist) new LoginSuccess().loginResult(input);
        else new LoginFail().loginResult(input);
    };

}
