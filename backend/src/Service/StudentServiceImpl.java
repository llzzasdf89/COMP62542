package Service;
import Dao.*;
import Entity.*;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {
    Scanner sc = new Scanner(System.in);

    @Override
    public String checkStatus(Long uniNum) {
        StudentDao stuDao = new StudentDao();
        return stuDao.getState(uniNum);
    }

    @Override
    public String showTimeTable(Long uniNum) {
        StudentDao stuDao = new StudentDao();
        ArrayList<Course> courses = stuDao.getCourseSelection(uniNum);
        JSONArray jsonArray = JSONArray.fromObject(courses);
        String cou = jsonArray.toString();
        System.out.println(cou);
        return cou;
    }

    @Override
    public void courseSelection(Long uniNum){
        StudentDao stuDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        Student student = new Student(uniNum,null);
        ArrayList<Course> optCourses = courseDao.getAllOptionalCourses();
        JSONArray jsonArray = JSONArray.fromObject(optCourses);
        String opt = jsonArray.toString();
        System.out.println(opt);
        System.out.println("Input the number of the course you want to choose:");
        String courseNum = String.valueOf(sc.next());
        if(courseNum.contains("COM")) {
            student.setSelectCourseStrategy(new ManCourseStrategy());
            student.selectCourse(new ManCourse(courseNum,null,null,null));
        }
        else {
            student.setSelectCourseStrategy(new OptCourseStrategy());
            student.selectCourse(new OptCourse(courseNum, null, null, null, null));
        }
        if(student.getStudentState() instanceof RegisteredState) {
            if (stuDao.judgeExist(uniNum, courseNum))
                System.out.println("You have choosen this course!");
            else {
                stuDao.addCourseSelection(uniNum, courseNum);
                System.out.println("Your courseselection results:");
                stuDao.getCourseSelection(uniNum);
            }
        }
    }

    @Override
    public void courseOutSelection(Long uniNum){
        StudentDao stuDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        courseDao.getAllOptionalCourses();
        System.out.println("Input the number of the course you want to be out of choice:");
        String courseNum = String.valueOf(sc.next());
        if(courseNum.contains("COM")) {
            System.out.println("You must take ManCourse!");
            return;
        }
        if(!stuDao.judgeExist(uniNum,courseNum))
            System.out.println("You have not choosen this course!");
        else{
            stuDao.removeCourseSelection(uniNum,courseNum);
            System.out.println("Your courseselection results:");
            stuDao.getCourseSelection(uniNum);
        }
    }

    @Override
    public void addSubActivity(Long uniNum) {
        CourseDao courseDao = new CourseDao();
        StudentDao stuDao = new StudentDao();
        System.out.println("Input the additional activity you want to add:");
        System.out.println("Activity code:");
        String activityCode = String.valueOf(sc.next());
        System.out.println("Activity name:");
        String activityName = String.valueOf(sc.next());
        System.out.println("Activity time:");
        String activityTime = String.valueOf(sc.next());
        Course activity = new ManCourse(activityCode,activityName,"Additional",activityTime);
        courseDao.addSubActivity(activity);
        stuDao.addCourseSelection(uniNum,activityCode);
        System.out.println("add this activity succeessfully!");
    }

    @Override
    public void removeSubActivity(Long uniNum) {
        CourseDao courseDao = new CourseDao();
        StudentDao stuDao = new StudentDao();
        System.out.println("Input the code of the additional activity you want to delete:");
        String activityCode = String.valueOf(sc.next());
        stuDao.removeCourseSelection(uniNum,activityCode);
        courseDao.removeSubActivity(activityCode);
        System.out.println("delete this activity succeessfully!");
    }

    @Override
    public void newsletterSubscribe(Long uniNum) {
        StudentDao stuDao = new StudentDao();
        System.out.println("Input the newsletter you want to subscribe:");
        System.out.println("Newsletter number:");
        String newsNum = String.valueOf(sc.next());
        stuDao.addNewsSelection(uniNum, newsNum);
    }

    @Override
    public void newsletterOutSubscribe(Long uniNum) {
        StudentDao stuDao = new StudentDao();
        System.out.println("Input the newsletter you do not want to subscribe:");
        System.out.println("Newsletter number:");
        String newsNum = String.valueOf(sc.next());
        stuDao.removeNewsSelection(uniNum, newsNum);
    }

    @Override
    public String showNewsletters(Long uniNum) {
        StudentDao stuDao = new StudentDao();
        ArrayList<Newsletter> newsletters = stuDao.getNewsSelection(uniNum);
        JSONArray jsonArray = JSONArray.fromObject(newsletters);
        String news = jsonArray.toString();
        System.out.println(news);
        return news;
    }

}

