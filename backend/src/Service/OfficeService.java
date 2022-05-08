package Service;

import Entity.Newsletter;

import java.util.ArrayList;

public interface OfficeService {
    public void addNewsletter(String newsNum);
    public void updateNewsletter(String newsNum);
    public void removeNewsletter(String newsNum);
    public void showNewsletter();
    public void sendReminder(String reminder);
}
