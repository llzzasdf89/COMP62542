package Entity;
import java.util.ArrayList;

public class StudentUnion {

    private static StudentUnion instance;

    public StudentUnion() {
    }

    public static synchronized StudentUnion createInstance() {
        if (instance == null)
            instance = new StudentUnion();
        return instance;
    }

    private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    public void addNewsletter(Newsletter newsletter) {
        newsletters.add(newsletter);
    }

    public void deleteNewsletter(Newsletter newsletter) {
        newsletters.remove(newsletter);
        System.out.println("newsletter deleted");
    }

    public void updateNewsletter(Newsletter newsletter, String content) {
        newsletter.newsLetterUpdate(content);
    }
}
