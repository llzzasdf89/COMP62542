package Service;

import Entity.Course;
import Entity.Newsletter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;

public interface StudentService {
    public JSONArray showTimeTable(Long uniNum);
    public void selectCourse(Long uniNum,String courseNum);
}
