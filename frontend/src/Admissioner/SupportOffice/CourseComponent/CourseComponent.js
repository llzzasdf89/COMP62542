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
        onClick={()=>this.switchView(this.state.studentIndex)}></Button> 
        </div>
        <div>course list of {this.state.currentStudent.name}</div>
        </div>} >
            <div className='studentCourseList'>
            <Divider orientation="center">Courses of {this.state.currentStudent.name}</Divider>
            <List 
            dataSource={this.state.currentStudent.course}
            renderItem = {
                (course) =>
                <List.Item actions={[<Button type='primary' shape='round' onClick={()=>this.updateCourseList(course,this.state.studentIndex,'delete')}>Delete</Button>]}>
                {course.title}
                    
                </List.Item>
            }
            >
            </List>
            </div>
            <Divider orientation='center' type="vertical" style={{height:'100%',margin:'0 1rem'}}></Divider>
            <div className='coursePoolList'>
            <Divider orientation="center">Course Available</Divider>
            <List 
            dataSource={this.state.coursePool}
            renderItem = {
                item=>{
                    const {course} = this.state.currentStudent //get the course which the student already has
                    if(course.some((i) => i.title === item.title)) return
                    else return <List.Item actions = {[<Button type='primary' shape='round' onClick={()=>this.updateCourseList(item,this.state.studentIndex,'add')}>Add</Button>]}>{item.title}</List.Item>
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