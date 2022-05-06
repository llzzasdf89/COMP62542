import React,{Component} from 'react'
import './Course.css'
import {Divider} from 'antd'  
import ListComponent from './listComponent'
import { useOutletContext } from 'react-router-dom'
import NotRegisteredTemplate from '../NotRegisteredTemplate'
import {request} from '../../util'
//Because mandatory Courses are static and will not change in the future,so these could be imported from timetable config file
//filter out the Mandatory courses, this will only return the object lists. But when output on the screen, we need to return their title
class Course extends Component{
    handleClick = (course,Request)=>{
        /**
         * Handle click is a function to communicate with sub component "list"
         * The real actor is the subcomponent 'list'
         * Anyway, this function aims to control the 'delete' and 'add course' operation
         */
        const {student,optCourse,optCoursesAvailable} = this.state
        if(Request === 'delete'){
            let idx = -1;
            optCoursesAvailable.push(course) //delete optCourse is to add course to Available list
            optCourse.forEach((item,index) => {if(item.title === course.title) idx = index})
            optCourse.splice(idx,1) //meanwhile, delete the item from optCourse list
            student.course.forEach((item) => {if(course.title === item.title) course.type = 'Optional Available'})
            const data = {
                removeCourse:[student.id]
            }
            request(data)
            this.setState({student,optCourse,optCoursesAvailable}) //update the view
        }
        else if (Request === 'add'){
            //The adding logic is reversed from adding, which is adding list to optCourse, but delete from available list
            const {optCourse, optCoursesAvailable} = this.state
            optCourse.push(course)
            let idx = -1;
            optCoursesAvailable.forEach((item,index) => {if(item.title === course.title) idx = index})
            optCoursesAvailable.splice(idx,1)
            const data = {
                addCourse:[student.id]
            }
            request(data)
            student.course.forEach((item) => {if(course.title === item.title) course.type = 'Optional'})
            this.setState({student,optCourse,optCoursesAvailable}) //update the view
        }
    }
    constructor(props){
        super(props)
        const {student} =props.student
        const {course} = student
        const manCourse = course.filter((course)=>course.type==='Mandatory')
        const optCourse = course.filter((course)=> course.type ==='Optional')
        const optCoursesAvailable = course.filter((course) => course.type==='Optional Available' )
        this.state = {
            student,
            manCourse,
            optCourse,
            optCoursesAvailable
        }
        /**
         * Notice this is important, because in React every dynamic data should be bound on the state object
         * or the view would not be updated as if we try to delete data
         */
    }
    render(){
        const {status} = this.state.student
        let template = <NotRegisteredTemplate></NotRegisteredTemplate>
        if(status === 'registered'){
            template = 
            <div className="courseContainer">
                <div className='courseContainer-header'></div>
                <div className='courseContainer-body'>
                    <div className='courseContainer-body-manCourseArea'>
                        <ListComponent listTitle='Your Mandatory Course' courseList={this.state.manCourse} handleClick={this.handleClick}></ListComponent>
                        <ListComponent listTitle="Your Optional Course" courseList = {this.state.optCourse} handleClick={this.handleClick}></ListComponent>
                    </div>
                    <Divider type='vertical' style={{height:'100%'}} dashed></Divider>
                    <div className='courseContainer-body-optCourseArea'>
                        <ListComponent listTitle="Optional Courses Available" courseList = {this.state.optCoursesAvailable} handleClick = {this.handleClick}></ListComponent>
                    </div>
                </div>
                </div>
        }
        return template
    }
}
export default ()=><Course student={useOutletContext()}></Course>