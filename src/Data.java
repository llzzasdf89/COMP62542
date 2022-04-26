import java.util.ArrayList;
//Concrete Container in Iterator Pattern
public class Data implements DataContainer{
    /**
     * Mock data:
     * Students is the list of all the students;
     * Courses is the list of all the Courses in this system;
     */
    private static Data data = new Data();
    private ArrayList<Student> Students = new ArrayList<Student>();
    private ArrayList<Course> Courses= new ArrayList<Course>();
    public Data (){
        if(data != null) return; //singleton pattern
        //Suppose we have ten optional courses and mandatory courses.
        for(int i = 0; i < 10; i++){
            Students.add(StudentFactory.createStudent(i));   
            Course tmp = CourseFactory.createCourse("ManCourse", String.valueOf(i), "ManCourse" + String.valueOf(i), null, null);
            tmp.initateCourse(); //this is to invocate the 'template method' in each course.
            Courses.add(tmp);
            tmp = CourseFactory.createCourse("OptCourse", String.valueOf(i),"OptCourse" + String.valueOf(i), null, null);
            tmp.initateCourse();
            Courses.add(tmp);
        }
    }
    public static Data getDataInstance (){
        return data;
    }
    //Concrete Iterator in Iterator Pattern
    private class StudentIterator implements DataIterator{
        int index;
        @Override
        public boolean hasNext() {
            if(index < Students.size()) return true;
            return false;
        }
        @Override
        public Object next() {
            if(this.hasNext()) return Students.get(index++);
            return null;
        }
        @Override
        public void add(Object obj){
            Students.add((Student)obj);
        }
        @Override
        public void remove(Object obj){
            Students.remove((Student)obj);
        }
    }
    //Concrete Iterator
    private class CourseIterator implements DataIterator{
        int index;
        @Override
        public boolean hasNext() {
            if(index < Courses.size()) return true;
            return false;
        }
        @Override
        public Object next() {
            if(this.hasNext()) return Courses.get(index++);
            return null;
        }
        @Override
        public void add(Object obj){
            Courses.add((Course)obj);
        }
        @Override
        public void remove(Object obj){
            Students.remove((Course)obj);
        }
    }
    @Override
    public DataIterator getStudentIterator() {
        return new StudentIterator();
    }
    @Override
    public DataIterator getCourseIterator() {
        return new CourseIterator();
    }
}
