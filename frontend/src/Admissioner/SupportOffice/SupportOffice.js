import React,{Component} from 'react'
import {List, Card,Button} from 'antd'
import CourseComponent from './CourseComponent/CourseComponent'
import {useOutletContext} from 'react-router-dom'
import './SupportOffice.css'
import {request} from '../../util'
//mock data
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
        const {students} = this.state
        if(query === 'add'){
            students[studentIndex].course.push(courseObj)
        }
        else if (query === 'delete'){
            students[studentIndex].course.forEach(
                (item,index) =>{
                    if(item.title === courseObj.title) return students[studentIndex].course.splice(index,1)
                }
            ) //use for Each to traverse all the course list, to find the one to be removed and remove it
        }
        this.setState({
            students
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
        const {Manager} = props.params
        const {students} = Manager
        this.state = {
            coursePool:course,
            students,
            studentView:false,
            studentIndex:0,
        }
    }
    render(){
        const {studentView} = this.state
        let template = <Card title="Student List">

        <List 
        dataSource={this.state.students}
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
        currentStudent = {this.state.students[this.state.studentIndex]}
        coursePool = {this.state.coursePool} 
        updateCourseList = {this.updateCourseList}
        switchView={this.switchView}></CourseComponent>
        return template
    }
}

export default ()=><SupportOffice params = {useOutletContext()}></SupportOffice>

