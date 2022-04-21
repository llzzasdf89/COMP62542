package StudentSys;

import java.util.ArrayList;

public class Newsletter {
    private String content;
    private ArrayList<Student> subscribers = new ArrayList<Student>();
    public Newsletter(String content){
        this.content = content;
    }

    public void addSubscriber(Student student){
        subscribers.add(student);
    }

    public void removeSubscriber(Student student){
        subscribers.remove(student);
    }

    public ArrayList<Student> getSubscribers() {
        return subscribers;
    }

    public void newsLetterUpdate(String n){
        content = n;
        notifyAllSubscribers();

    }

    public void notifyAllSubscribers(){
        for (Student student:subscribers){
            System.out.println(content);
            student.newsLetterUpdata();
        }

    }
}
