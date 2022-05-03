package Service;

import Entity.Course;
import Entity.Newsletter;

import java.util.ArrayList;

public interface StudentService {
    public String checkStatus(Long uniNum);
    public String showTimeTable(Long uniNum);
    public void courseSelection(Long uniNum);
    public void courseOutSelection(Long uniNum);
    public void addSubActivity(Long uniNum);
    public void removeSubActivity(Long uniNum);
    public void newsletterSubscribe(Long uniNum);
    public void newsletterOutSubscribe(Long uniNum);
    public String showNewsletters(Long uniNum);
}
