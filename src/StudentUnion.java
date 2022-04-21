package StudentSys;

import java.util.ArrayList;

public class StudentUnion {

    private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();
    public StudentUnion(){
    }

    public void addNewsletter(Newsletter newsletter){
        newsletters.add(newsletter);
    }

    public void deleteNewsletter(Newsletter newsletter){
        newsletters.remove(newsletter);
    }

    public void updateNewsletter(Newsletter newsletter, String content){
        newsletter.newsLetterUpdate(content);
    }
}
