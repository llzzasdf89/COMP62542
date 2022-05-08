package Entity;
import Dao.NewsletterDao;

import java.util.ArrayList;

public class StudentUnion {

    private static StudentUnion instance;
    NewsletterDao newsletterDao = new NewsletterDao();

    public StudentUnion() {
    }

    public static synchronized StudentUnion createInstance() {
        if (instance == null)
            instance = new StudentUnion();
        return instance;
    }

    //private ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    public void addNewsletter(Newsletter newsletter) {
        newsletterDao.addNewsletter(newsletter);
    }

    public void deleteNewsletter(Newsletter newsletter) {
        newsletterDao.removeNewsletter(newsletter.getNewsNum());
        Newsletter.newsletters.remove(newsletter);

    }

    public void updateNewsletter(Newsletter newsletter, String content) {
        newsletterDao.updateNewsletter(newsletter.getNewsNum(),content);
        newsletter.newsLetterUpdate(content);
        newsletter.notifyAllSubscribers();
    }
}
