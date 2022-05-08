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
        StudentDao studentDao  = new StudentDao();
        studentDao.deleteTables();

        Student s1 = new Student(1,"a");
        Student s2 = new Student(2,"b");
        OptCourse c1 = new OptCourse("c1","OPT1","CS","OPT","Mon","09:00:00","10:00:00");

        OptCourse c2 = new OptCourse("c2","OPT1","CS","OPT","Mon","11:00:00","12:00:00");
        OptCourse c3 =new OptCourse("c3","C3","CS","MAN","Mon","14:00:00","16:00:00");


        c1.addSubActivity(c3);

        try {

            HttpServer server = HttpServer.create(new InetSocketAddress(8001),0);
            System.out.println("Running the server on the port 8001");


            server.createContext("/",new TestHandler());
            server.start();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static class TestHandler implements HttpHandler{

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
                    exchange.sendResponseHeaders(200, allInfo.toString().getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write(allInfo.toString().getBytes());
                        os.flush();
                    }
                    break;

                case "login":
                    System.out.println("get command");
                    StudentDao  studentDao1 = new StudentDao();
                    System.out.println("login request");
                    JSONObject student  = studentDao1.getStudentInfo(Long.parseLong(command[1]));
                    System.out.println(student);
                    exchange.sendResponseHeaders(200, student.toString().getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write(student.toString().getBytes());
                        os.flush();
                    }catch (EOFException e){
                        e.printStackTrace();
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
                    try(OutputStream os = exchange.getResponseBody()) {
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
                    try(OutputStream os = exchange.getResponseBody()) {
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
                        try(OutputStream os = exchange.getResponseBody()) {
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
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
                    }
                    break;

                case "admissionSendReminder":
                    Reminder reminder = new Reminder(command[1],command[2]);
                    studentAdmissionsOffice.sendReminder(reminder);
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
                    }
                    break;

                case "deleteNewsletter":
                    for (Newsletter n : Newsletter.newsletters) {
                        if (n.getNewsNum().equals(command[1])) {
                            studentUnion.deleteNewsletter(n);
                        }
                    }
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
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
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
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
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
                    }
                    break;
                //case "supportSendReminder":
                case "addNewsletter":
                    Newsletter n = new Newsletter(command[1],command[2]);
                    studentUnion.addNewsletter(n);
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
                    }
                    break;

                case "updateNewsletter":
                    for (Newsletter n1 : Newsletter.newsletters) {
                        if (n1.getNewsNum().equals(command[1])) {
                            studentUnion.updateNewsletter(n1,command[2]);
                        }
                    }
                    exchange.sendResponseHeaders(200,"true".getBytes().length);
                    try(OutputStream os = exchange.getResponseBody()) {
                        os.write("true".getBytes());
                        os.flush();
                    }
                    break;
            }
            exchange.close();
        }
    }


}