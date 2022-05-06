package Service;

import Dao.NewsletterDao;
import Dao.OfficeDao;
import Entity.Newsletter;
import Entity.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class OfficeServiceImpl implements OfficeService{
    Scanner sc = new Scanner(System.in);
    @Override
    public void addNewsletter(String newsNum) {
        NewsletterDao newsDao = new NewsletterDao();
        System.out.println("Input the newsletter content you want to add:");
        String content = String.valueOf(sc.next());
        Newsletter newsletter = new Newsletter(content,newsNum);
        newsDao.addNewsletter(newsletter);
        System.out.println("add this newsletter succeessfully!");
    }

    @Override
    public void updateNewsletter(String newsNum) {
        NewsletterDao newsDao = new NewsletterDao();
        System.out.println("Input the new content of the newsletter:");
        String content = String.valueOf(sc.next());
        newsDao.updateNewsletter(newsNum,content);
        System.out.println("update this newsletter succeessfully!");
    }

    @Override
    public void removeNewsletter(String newsNum) {
        NewsletterDao newsDao = new NewsletterDao();
        newsDao.removeNewsletter(newsNum);
        System.out.println("delete this newsletter succeessfully!");
    }

    @Override
    public void showNewsletter() {
        NewsletterDao newsDao = new NewsletterDao();
        newsDao.getAllNewsletters();
    }

    @Override
    public void sendReminder(String reminder) {
        OfficeDao offDao = new OfficeDao();
        ArrayList<Student> students  = offDao.getAllPendingStudents();

    }
}
