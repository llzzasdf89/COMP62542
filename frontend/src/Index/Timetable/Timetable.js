import React,{Component} from 'react'
import './Timetable.css'
import {DayPilotCalendar} from "daypilot-pro-react";
import {TimetableConfig} from './TimetableConfig';
class Timetable extends Component{
    render(){
        return <div className='timetableContainer'> 
        <DayPilotCalendar
        {...TimetableConfig}
        /
        >
        </div>
    }
}
export default Timetable