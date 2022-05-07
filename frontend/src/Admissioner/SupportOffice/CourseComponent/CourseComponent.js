import React, {Component} from 'react'
import {List,Card, Button, Divider} from 'antd'
import { StepBackwardFilled } from '@ant-design/icons';
import './CourseComponent.css'
//This component correspond to a page of specfic student's course list and selection list in the Support Office
class CourseComponent extends Component {
    constructor (props){
        super(props)
        const {currentStudentInfo} = props
        const {currentStudent,coursePool,switchView,studentIndex,updateCourseList} = currentStudentInfo
        this.state = {
            currentStudent,
            coursePool,
            studentIndex
        }
        this.switchView = switchView
        this.updateCourseList = updateCourseList
    }
    render(){
        return <Card
        style = {{height:'100%',overflow:'hidden'}}
        bodyStyle={{display:'flex',flexDirection:'row'}}
        title={<div className='titleArea'><div> 
        <Button icon={<StepBackwardFilled></StepBackwardFilled>} 
        type='link' 
        shape='round' 
        size='small' 
        onClick={()=>this.switchView(this.props.currentStudentInfo.studentIndex)}></Button> 
        </div>
        <div>course list of {this.props.currentStudentInfo.currentStudent.name}</div>
        </div>} >
            <div className='studentCourseList'>
            <Divider orientation="center">Courses of {this.props.currentStudentInfo.currentStudent.name}</Divider>
            <List 
            dataSource={this.props.currentStudentInfo.currentStudent.courses}
            renderItem = {
                (course) =>{
                    if(Array.isArray(course)) return 
                    return <List.Item actions={[<Button type='primary' shape='round' onClick={()=>this.updateCourseList(course,this.props.currentStudentInfo.studentIndex,'delete')}>Delete</Button>]}>
                    {course.name}
                    
                     </List.Item>
                }
                
            }
            >
            </List>
            </div>
            <Divider orientation='center' type="vertical" style={{height:'100%',margin:'0 1rem'}}></Divider>
            <div className='coursePoolList'>
            <Divider orientation="center">Course Available</Divider>
            <List 
            dataSource={this.props.currentStudentInfo.coursePool}
            renderItem = {
                item=>{
                    const {courses} = this.props.currentStudentInfo.currentStudent//get the course which the student already has
                    if(courses.some((i) => i.courseNum === item.courseNum)) return
                    else return <List.Item actions = {[<Button type='primary' shape='round' onClick={()=>this.updateCourseList(item,this.props.currentStudentInfo.studentIndex,'add')}>Add</Button>]}>{item.name}</List.Item>
                }
            }>
            </List>
            </div>
        </Card>
    }
}

export default (props)=>  
//props is a variable passed from parent page SupportOffice, contains the information of this student,his selected courses  and his available courses
<CourseComponent currentStudentInfo={props}></CourseComponent>