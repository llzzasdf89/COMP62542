# COMP62542Project
COMP62542Project is a Java group project of COMP62542 in the University of Manchester.
Group member includes :<br/>
Jiong Gao<br/>
Xiaoyu Shi<br/>
Zhi Li
<br/>
Established on 15. April. 2022, the project aims to establish a student registeration system with multiple design patterns. <br/>
The patterns involved includes : 
1. Strategy
2. State
3. Decorator
4. Adapter
5. Composite
6. Template
7. Visitor
8. Observer
9. Iterator
10. Factory
11. Singleton
12. Model-View-Controller

# Requirements of system included:
1. Login. Assume each student has been given a unique number to log into the system; no
password is required.
2. Once logged in, the student can check her/his registration status. Assume the status of a
student is either fully registered, registration pending or not registered.
3. For a fully registered student, she/he can view her/his basic timetable and add additional
activities such as tutorials and supervision meetings to the basic timetable. Assume the
timetable is the same for every week, so that you only need to consider one week’s timetable.
4. For a fully registered student, she/he can choose an optional course unit from a set of course
units and the student can also opt-out a course unit from the optional course units. The
optional courses are offered by Computer Science and Mathematics Departments.
5. For a fully registered student, she/he can subscribe to one or more newsletters published by
the student union.
6. For a fully registered student, she/he will allow the student support office too add or remove
some courses from her/his course list.
7. For a student pending for registration (with the registration pending status), she/he will
receive a daily reminder from the student admissions office to remind them of the deadline
for payment. 

## Usage:
### (First time) get the project
```
git clone https://github.com/llzzasdf89/COMP62542.git
```

### in development:
```
update code before developing:
git fetch origin master:XXX(Any name is permitted);
git checkout XXX
```
```
when finished your work:
git add .;
git commit -m 'comment of your work done in this commit. For exmaple, finished Factory pattern. The pattern is used in XXX class..' 
git push -u origin XXX (XXX could be named yourself)
```
```
Merge with master branch:
Go to the repository page :https://github.com/llzzasdf89/COMP62542
click the compare & pull request;
in the new page, comment what you did in the commit. It is almost the same with the comment in the previous step;
waiting for Zhi Li's authentication. 
```

### compile
Make sure JVM 10.0 or higher is prepared.
```
javac src/*.java -d ./StudentSys;
cd StudentSys;
java Run;
```

### Paths

```
.
├── README.md
├── designdoc //contains the documents of each design pattern. 
└── src //contains the source code of backend

```
### Other issues:
The development process could be tracked in the **_project_** page, remeber to check that before development ;