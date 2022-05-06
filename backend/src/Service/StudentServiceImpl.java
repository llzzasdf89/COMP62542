package Service;
import Dao.*;
import Entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {
    Scanner sc = new Scanner(System.in);

    @Override
    public JSONArray showTimeTable(Long uniNum) {
        StudentDao stuDao = new StudentDao();
        ArrayList<Course> courses = stuDao.getCourseSelection(uniNum);
        JSONArray cou = JSONArray.fromObject(courses);
        for (Course c1: courses) {
            cou.add(stuDao.getSubActivites(c1.getCourseNum()));
        }
        return cou;
    }

    @Override
    public void selectCourse(Long uniNum, String courseNum){
        StudentDao stuDao = new StudentDao();
        if (stuDao.judgeExist(uniNum, courseNum)) {
            System.out.println("You have choosen this course!");
            return;
        }
        stuDao.addCourseSelection(uniNum, courseNum);
    }
}

