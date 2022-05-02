package Entity;
import java.util.ArrayList;

public class Newsletter {
    private String content;
    private String newsNum;
    private ArrayList<Student> subscribers = new ArrayList<Student>();

    public Newsletter(String content, String newsNum) {
        this.content = content;
        this.newsNum = newsNum;
    }
    public Newsletter(){}
    public void setContent(String Content){
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setNewsNum(String newsNum) {
        this.newsNum = newsNum;
    }
    public String getNewsNum() {
        return newsNum;
    }

    public void addSubscriber(Student student) {
        subscribers.add(student);
    }

    public void removeSubscriber(Student student) {
        subscribers.remove(student);
    }

    public ArrayList<Student> getSubscribers() {
        return subscribers;
    }

    public void newsLetterUpdate(String n) {
        content = n;
        notifyAllSubscribers();

    }

    public void notifyAllSubscribers() {
        for (Student student : subscribers) {
            System.out.println(content);
            student.newsLetterUpdata();
        }

    }
}
