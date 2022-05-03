import React,{Component} from 'react'
import './Timetable.css'
import {DayPilotCalendar} from "daypilot-pro-react";
import {TimetableConfig} from './TimetableConfig';
import { useOutletContext } from 'react-router-dom';
import NotRegisteredTemplate from '../NotRegisteredTemplate';
class Timetable extends Component{
    constructor(props){
        super(props)
        const {student} = props
        this.state = student
    }
    render(){
        const {student} = this.state
        let template = <NotRegisteredTemplate></NotRegisteredTemplate>
        if(student.status === 'registered'){
            template = 
            <div className='timetableContainer'> 
                <DayPilotCalendar
                {...TimetableConfig(student)}
                /
                >
            </div>
        }
        return template
    }
}
export default ()=> <Timetable student={useOutletContext()}></Timetable>