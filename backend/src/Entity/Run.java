package Entity;

import Dao.CourseDao;
import Dao.NewsletterDao;
import Dao.ReminderDao;
import Dao.StudentDao;
import Service.OfficeServiceImpl;
import Service.StudentServiceImpl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
//import com.google.gson.Gson;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;


public class Run {

    public static void main(String[] args) {
        Student s1 = new Student(1,"a");
        Student s2 = new Student(2,"b");
        OptCourse c1 = new OptCourse("c1","OPT1","CS","OPT","Mon");
        OptCourse c2 = new OptCourse("c2","OPT1","CS","OPT","Mon");
        OptCourse c3 =new OptCourse("c3","C3","CS","M1","Mon");
        c1.addSubActivity(c3);

        try {
            //启动服务器的主要方法是通过引入HttpServer这个类，这个类在com.sun.net包下
            HttpServer server = HttpServer.create(new InetSocketAddress(8001),0);
            System.out.println("Running the server on the port 8001");//假设我们的服务器运行在8001端口
            //createContext API 是HttpServer类的一个关键API，它主要负责处理的是路由
            //下面这句话的意思是要监听/test这个路由下的请求，实施监听的对象是这个叫做TestHandler的对象
            server.createContext("/test",new TestHandler());
            server.start();//启动服务器的指令
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static class TestHandler implements HttpHandler{
        //注意重写Handler的时候最主要的事情就是重写这个Handle方法
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StudentDao studentDao = new StudentDao();
            StudentUnion studentUnion = StudentUnion.createInstance();
            StudentSupportOffice studentSupportOffice = StudentSupportOffice.createInstance();
            StudentAdmissionsOffice studentAdmissionsOffice = StudentAdmissionsOffice.createInstance();

            final String origin = exchange.getRequestHeaders().getFirst("Origin");
            if(origin != null) exchange.getResponseHeaders().add("Access-Control-Allow-Origin", origin);
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");

            String result = exchange.getRequestURI().getQuery();
            String[] command = result.split("&");
            System.out.println(command[0]);

            switch (command[0]){
                case "Admissioner":
                    JSONObject allInfo = new JSONObject();

                    CourseDao courseDao = new CourseDao();
                    courseDao.getAllCourseJson();

                    NewsletterDao newsletterDao = new NewsletterDao();
                    JSONArray newsletterJson = newsletterDao.getAllNewsletters();

                    ReminderDao reminderDao = new ReminderDao();

                    allInfo.element("reminders",reminderDao.getReminderJson());
                    allInfo.element("students",studentDao.getStudentJson());
                    allInfo.element("courses",courseDao.getAllCourseJson());
                    allInfo.element("newsletters",newsletterJson);
                    exchange.sendResponseHeaders(200, allInfo.toString().getBytes().length);//然后是设置响应的代码，默认我们为200，第二个参数是这个响应的数据的长度
                    try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                        os.write(allInfo.toString().getBytes());
                        os.flush();
                    }
                    break;

                case "login":
                    JSONObject student = studentDao.getStudentInfo(Long.parseLong(command[1]));
                    System.out.println("login request");
                    exchange.sendResponseHeaders(200, student.toString().getBytes().length);//然后是设置响应的代码，默认我们为200，第二个参数是这个响应的数据的长度
                    try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                        os.write(student.toString().getBytes());
                        os.flush();
                    }
                    break;
                case "registration":
                    System.out.println("xxxx");
                    for (Student s: Student.students) {
                        if (s.getUniNum()==Long.parseLong(command[1])){
                            s.state();
                        }
                    }
                    String b = "true";
                    exchange.sendResponseHeaders(200,b.getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                        os.write(b.getBytes());
                        os.flush();
                    }
                    break;
                case "pay":
                    System.out.println("pay command");
                    for (Student s: Student.students) {
                        if (s.getUniNum()==Long.parseLong(command[1])){
                            s.state();
                        }
                    }
                    String c = "true";
                    exchange.sendResponseHeaders(200,c.getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                        os.write(c.getBytes());
                        os.flush();
                    }
               // case "selectCourse":
              //  case "quitCourse":
                    break;
                case "subscribeNewsletter":
                    for (Student s: Student.students) {
                        if (s.getUniNum()==Long.parseLong(command[1])){
                            System.out.println("get student");
                            for (Newsletter newsletter: Newsletter.newsletters) {
                                System.out.println(newsletter.getNewsNum());
                                System.out.println(command[2]);

                                if(newsletter.getNewsNum().equals(command[2])){
                                    System.out.println("get newsletter");
                                    s.subscribeNewsletter(newsletter);
                                }
                            }
                        }
                        String d= "true";
                        exchange.sendResponseHeaders(200,d.getBytes().length);
                        try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                            os.write(d.getBytes());
                            os.flush();
                        }
                    }
                    break;


                case "cancelNewsletter":
                    for (Student s: Student.students) {
                        if (s.getUniNum()==Long.parseLong(command[1])){
                            for (Newsletter newsletter: Newsletter.newsletters) {
                                if(newsletter.getNewsNum().equals(command[2])){
                                    s.cancelNewsletter(newsletter);
                                }
                            }
                        }
                    }
                    break;

                case "admissionSendReminder":
                    Reminder reminder = new Reminder(command[1],command[2]);
                    studentAdmissionsOffice.sendReminder(reminder);
                    break;

                case "deleteNewsletter":
                    for (Newsletter n : Newsletter.newsletters) {
                        if (n.getNewsNum().equals(command[1])) {
                            studentUnion.deleteNewsletter(n);
                        }
                    }
                    break;

                case "addCourse":
                    for (Student s: Student.students) {
                        if (s.getUniNum()==Long.parseLong(command[1])){
                            for (Course course: Course.courses) {
                                if(course.getCourseNum().equals(command[2])){
                                    s.selectCourse(course);
                                }
                            }
                        }
                    }
                    break;
                case "removeCourse":
                    for (Student s: Student.students) {
                        if (s.getUniNum()==Long.parseLong(command[1])){
                            for (Course course: Course.courses) {
                                if(course.getCourseNum().equals(command[2])){
                                    studentSupportOffice.removeCourse(s,course);
                                }
                            }
                        }
                    }
                    break;
                //case "supportSendReminder":
                case "addNewsletter":
                    Newsletter n = new Newsletter(command[1],command[2]);
                    studentUnion.addNewsletter(n);
                    String d = "add";
                    exchange.sendResponseHeaders(200,d.getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                        os.write(d.getBytes());
                        os.flush();
                    }
                    System.out.println(Newsletter.newsletters);
                    break;

                case "updateNewsletter":
                    for (Newsletter n1 : Newsletter.newsletters) {
                        if (n1.getNewsNum().equals(command[1])) {
                            studentUnion.updateNewsletter(n1,command[2]);
                        }
                    }
                    break;
            }
            exchange.close();
        }
    }
}