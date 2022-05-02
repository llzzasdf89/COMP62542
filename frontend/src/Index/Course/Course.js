import React,{Component} from 'react'
import './Course.css'
import {mockCourses} from '../Timetable/TimetableConfig'
import {Divider} from 'antd'  
import ListComponent from './listComponent'
//Because mandatory Courses are static and will not change in the future,so these could be imported from timetable config file
//filter out the Mandatory courses, this will only return the object lists. But when output on the screen, we need to return their title
const manCourses = mockCourses.filter(course=>course.type==='Mandatory')
const optCourses = mockCourses.filter(course=>course.type==='Optional')
const optCoursesAvailable = mockCourses.filter(course=>course.type==='Optional')
optCoursesAvailable[0] = {...optCourses[0]}
optCoursesAvailable[0].title = 'Modelling data on the web' //for convinience, change both courses' title to distinct them
class Course extends Component{
    handleClick = (course,request)=>{
        /**
         * Handle click is a function to communicate with sub component "list"
         * The real actor is the subcomponent 'list'
         * Anyway, this function aims to control the 'delete' and 'add course' operation
         */
        const {optCourses,optCoursesAvailable} = this.state
        if(request === 'delete'){
            let idx = -1;
            optCoursesAvailable.push(course) //delete optCourse is to add course to Available list
            optCourses.forEach((item,index) => {if(item.title === course.title) idx = index})
            optCourses.splice(idx,1) //meanwhile, delete the item from optCourse list
            this.setState({optCourses,optCoursesAvailable}) //update the view
        }
        else if (request === 'add'){
            //The adding logic is reversed from adding, which is adding list to optCourse, but delete from available list
            const {optCourses, optCoursesAvailable} = this.state
            optCourses.push(course)
            let idx = -1;
            optCoursesAvailable.forEach((item,index) => {if(item.title === course.title) idx = index})
            optCoursesAvailable.splice(idx,1)
            this.setState({
                optCoursesAvailable,
                optCourses
            })
        }
    }
    constructor(props){
        super(props)
        /**
         * Notice this is important, because in React every dynamic data should be bound on the state object
         * or the view would not be updated as if we try to delete data
         */
        this.state = { //binding these courses on the state object.
            manCourses,
            optCourses,
            optCoursesAvailable
        }
    }
    render(){
        return <div className="courseContainer">
                <div className='courseContainer-header'></div>
                <div className='courseContainer-body'>
                    <div className='courseContainer-body-manCourseArea'>
                        <ListComponent listTitle='Your Mandatory Course' courseList={this.state.manCourses} handleClick={this.handleClick}></ListComponent>
                        <ListComponent listTitle="Your Optional Course" courseList = {this.state.optCourses} handleClick={this.handleClick}></ListComponent>
                    </div>
                    <Divider type='vertical' style={{height:'100%'}} dashed></Divider>
                    <div className='courseContainer-body-optCourseArea'>
                        <ListComponent listTitle="Optional Courses Available" courseList = {this.state.optCoursesAvailable} handleClick = {this.handleClick}></ListComponent>
                    </div>
                </div>
                
                </div>
    }
}
export default Course