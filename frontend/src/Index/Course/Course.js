import React,{Component} from 'react'
import './Course.css'
import {Divider, Modal} from 'antd'  
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
        const {student,optCourse,optCoursesAvailable,manCourse} = this.state
        if(Request === 'delete'){
            const params = {
                removeCourse:[student.uniNum,course.courseNum]
            }
            request(params).then(resolve=>{
                if(resolve === 'true'){
                    let idx = -1;
                    optCoursesAvailable.push(course) //delete optCourse is to add course to Available list
                    optCourse.forEach((item,index) => {if(item.courseNum === course.courseNum) idx = index})
                    optCourse.splice(idx,1) //meanwhile, delete the item from optCourse list
                    student.courses = manCourse.concat(optCourse)
                    Modal.success({
                        content:"Delete course success"
                    })
                    return this.setState({student,optCourse,optCoursesAvailable}) //update the view
                }
                return Modal.error({
                    content:'Delete course failed, please check the server'
                })
            },reject=>{
                return Modal.error({
                    content:'Delete course failed, please check the server'
                })
            })
        }
        else if (Request === 'add'){
            //The adding logic is reversed from delete, which is adding course to optCourse, but delete from available list
            const params = {
                addCourse:[student.uniNum,course.courseNum]
            }
            request(params).then(resolve=>{
                if(resolve==='true'){
                    optCourse.push(course)
                    let idx = -1;
                    optCoursesAvailable.forEach((item,index) => {if(item.courseNum === course.courseNum) idx = index})
                    optCoursesAvailable.splice(idx,1)
                    student.courses = manCourse.concat(optCourse)
                    Modal.success({
                        content:'add course success'
                    })
                    return  this.setState({student,optCourse,optCoursesAvailable}) //update the view
                }
                Modal.error({
                    content:'add course failed, please check the status of server'
                })
            },reject=>{
                Modal.error({
                    content:'add course failed, please check the status of server'
                })
            })

        }
    }
    constructor(props){
        super(props)
        const {student} =props.student
        this.state = {
            student,
            manCourse:[],
            optCourse:[],
            optCoursesAvailable:[]
        }
        /**
         * Notice this is important, because in React every dynamic data should be bound on the state object
         * or the view would not be updated as if we try to delete data
         * Generally, in this component, constructor is used for initalize the data to empty value;
         * the specific data in the components will be fetched in the componentDidMount function
         */
    }
    componentDidMount(){
        //React lifecycle function, this lifecycle is used to rerender component because some data should be fetched by asychronus function
        const params = {
            Admissioner:null
        }
        request(params).then((resolve)=>{
            //send another request to ask for course pool
            const data = JSON.parse(resolve);
            const {student} = this.state
            const {courses} = this.state.student
            const coursePool = data.courses;
            const manCourse = courses.filter((course)=>course.courseType==='M1')
            const optCourse = courses.filter((course)=> course.courseType ==='OPT')
            const optCoursesAvailable = coursePool.filter((course) => {
                if(course.courseType==='MAN') return false
               for(let c of optCourse){
                   if(c.courseNum === course.courseNum) return false
               }
               return true
            } )
            this.setState ({
            student,
            manCourse,
            optCourse,
            optCoursesAvailable
        }) //rerender the components, including the subcomponent
        },()=>{
            return Modal.error({
                content:'get the course information failed, please check the server'
            })
        })
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
                        <ListComponent listTitle="Optional Courses/Activities Available" courseList = {this.state.optCoursesAvailable} handleClick = {this.handleClick}></ListComponent>
                    </div>
                </div>
                </div>
        }
        return template
    }
}
export default ()=><Course student={useOutletContext()}></Course>