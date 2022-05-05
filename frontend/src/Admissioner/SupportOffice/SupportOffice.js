import React,{Component} from 'react'
import {List, Card,Button} from 'antd'
import CourseComponent from './CourseComponent/CourseComponent'
import './SupportOffice.css'
//mock data
const student = [
    {
        name:'Zhi Li',
        studentID:'10846881',
        course:[
            {
                title:'Software Engineering',
                startTime:"2022-05-02T09:00:00",
                endTime:'2022-05-02T11:00:00',
                type:'Mandatory'
            },
            {
                title:'Querying Data on the Web',
                startTime:'2022-05-04T15:00:00',
                endTime:'2022-05-04T17:00:00',
                type:'Optional',
                department:"Mathematics"
            },
            {
                title:'Modelling data on the web',
                startTime:'2022-05-05T15:00:00',
                endTime:'2022-05-05T17:00:00',
                type:'Optional Available',
                department:"Computer Science"
            }
        ],
    },
    {
        name:'WX',
        studentID:'1000086',
        course:[]
    },
    {
        name:'Test Student 2',
        studentID:'10000087',
        course:[
            {
                title:'Querying Data on the Web',
                startTime:'2022-05-04T15:00:00',
                endTime:'2022-05-04T17:00:00',
                type:'Optional',
                department:"Mathematics"
            }
        ]
    }
]
const course = [
    {
        title:'Software Engineering',
        startTime:"2022-05-02T09:00:00",
        endTime:'2022-05-02T11:00:00',
        type:'Mandatory'
    },
    {
        title:'Querying Data on the Web',
        startTime:'2022-05-04T15:00:00',
        endTime:'2022-05-04T17:00:00',
        type:'Optional',
        department:"Mathematics"
    },
    {
        title:'Modelling data on the web',
        startTime:'2022-05-05T15:00:00',
        endTime:'2022-05-05T17:00:00',
        type:'Optional Available',
        department:"Computer Science"
    },
    {
        title:'System Governance',
        startTime:'2022-05-05T15:00:00',
        endTime:'2022-05-05T17:00:00',
        type:'Mandatory'
    },
    {
        title:'Artificial Intelligence',
        startTime:'2022-05-05T15:00:00',
        endTime:'2022-05-05T17:00:00',
        type:'Optional',
        department:'Computer science'
    }
]

class SupportOffice extends Component{
    //function which receives course and update it according to the query
    updateCourseList = (courseObj,studentIndex,query)=>{
        const {student} = this.state
        if(query === 'add'){
            student[studentIndex].course.push(courseObj)
        }
        else if (query === 'delete'){
            student[studentIndex].course.forEach(
                (item,index) =>{
                    if(item.title === courseObj.title) return student[studentIndex].course.splice(index,1)
                }
            ) //use for Each to traverse all the course list, to find the one to be removed and remove it
        }
        this.setState({
            student
        })//update UI
    }
    //Control the subpage's display
    switchView = index=>{
        //studentView is a boolean variable to indicate which component to display
        let {studentView} = this.state
        studentView = !studentView
        this.setState({
            studentView,
            studentIndex:index
        })
    }
    constructor(props){
        super(props)        
        this.state = {
            coursePool:course,
            student,
            studentView:false,
            studentIndex:0,
        }
    }
    render(){
        const {studentView} = this.state
        let template = <Card title="Student List">

        <List 
        dataSource={this.state.student}
        renderItem = {
        (student,index) => 
        <List.Item 
        actions = {[<Button type='primary' shape='round' onClick={()=>this.switchView(index)}>View the courses</Button>]}>
        {student.name}
        </List.Item>}
        >

        </List>
        </Card>
        if(studentView) 
        //Course component is a encapsulated component in the sub directory
        template =  <CourseComponent 
        studentIndex = {this.state.studentIndex}
        currentStudent = {this.state.student[this.state.studentIndex]}
        coursePool = {this.state.coursePool} 
        updateCourseList = {this.updateCourseList}
        switchView={this.switchView}></CourseComponent>
        return template
    }
}

export default ()=><SupportOffice></SupportOffice>

