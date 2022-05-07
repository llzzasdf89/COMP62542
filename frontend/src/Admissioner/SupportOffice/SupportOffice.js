import React,{Component} from 'react'
import {List, Card,Button, Modal} from 'antd'
import CourseComponent from './CourseComponent/CourseComponent'
import {useOutletContext} from 'react-router-dom'
import './SupportOffice.css'
import {request} from '../../util'
class SupportOffice extends Component{
    //function which receives course and update it according to the query
    updateCourseList = (courseObj,studentIndex,query)=>{
        const {students,coursePool} = this.state
        console.log(studentIndex)
        if(query === 'add'){
            const params = {
                addCourse:[students[studentIndex].uniNum, courseObj.courseNum]
            }
            request(params).then(resolve=>{
                
                if(resolve === 'true'){
                    students[studentIndex].courses.push(courseObj)
                    Modal.success({
                        content:'add course success'
                    })
                    return this.setState({
                        students,
                        coursePool
                    })
                }
                Modal.error({
                    content:"add course failed, please check the server status"
                })
            },reject=>{
                Modal.error({
                    content:"add course failed, please check the server status"
                })
            })
        }
        else if (query === 'delete'){
            const params = {
                removeCourse:[students[studentIndex].uniNum,courseObj.courseNum]
            }
            request(params).then(resolve=>{
                if(resolve === 'true'){
                    students[studentIndex].courses.forEach(
                        (item,index) =>{
                            if(item.courseNum === courseObj.courseNum) students[studentIndex].courses.splice(index,1)
                        }
                    ) //use for Each to traverse all the course list, to find the one to be removed and remove it
                    Modal.success({
                        content:'Remove course sucess'
                    })
                    return this.setState({
                        students
                    })//update UI
                }
                return Modal.error({
                    content:"Remove course failed, please check the stauts of the server"
                })
            },(reject)=>{
                return Modal.error({
                    content:"Remove course failed, please check the stauts of the server"
                })
            })
        }
    }
    //Control the subpage's display
    switchView = index=>{
        //studentView is a boolean variable to indicate which component to display
        let {studentView,students} = this.state
        if(students[index].status != 'registered'){
            return Modal.error({
                content:"this student is not registered, so we could not edit courses"
            })
        }
        studentView = !studentView
        this.setState({
            studentView,
            studentIndex:index
        })
    }
    constructor(props){
        super(props)
        const {Manager} = props.params
        const {students,courses} = Manager
        this.state = {
            coursePool:courses,
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

