//config of the Timetable, including the how many days to display, the time range...etc.
import {DayPilot} from "daypilot-pro-react";
//define the form options when creating the new event in the timetable
const generateCourseEvent = (course)=>{
    /**
     * Because each course event in the timetable is immutable 
     * The only difference is that each course's time slot.
     * So this function is to quickly generate a template course event in the timetable
     * */
    const courseEvent = {
        id:course.title,
        deleteDisabled:true,
        moveDisabled:true,
        doubleClickDisabled:true,
        resizeDisabled:true,
        clickDisabled:true,
        start:course.startTime,
        end:course.endTime,
        text:course.title
    }
    return courseEvent
}
const generateActivityEvent = activity=>{
    const activityEvent = {
        id:activity.id,
        text:activity.type + '\n' + activity.course,
        moveDisabled:true,
        doubleClickDisabled:true,
        resizeDisabled:true,
        clickDisabled:true,
        start:activity.startTime,
        end:activity.endTime
    }
    return activityEvent
}
const TimetableConfig = (student)=>{
    console.log(student)
    const activityOptions = [
        {
            name:'Supervision Meeting',
            id:'Supervision Meeting'
        },
        {
            name:'Tutorial',
            id:'Tutorial'
        }
    ]
    const courseOptions = student.course.map((course)=>{
        return {
            name:course.title,
            id:course.title
        }
    })
    const deleteEvent = (e)=>{
        console.log(e)
    }
    const form = [
        {name:'Create Activity', id:'CreateActivity', options:activityOptions},
        {name:'From Course', id:'FromCourse',options:courseOptions}
    ] //define the form fields when creating even in the timetable, the first field ask for choosing activity type, the second for course
    const data = {
    'CreateActivity':activityOptions[0].name, //set the default option of the form
    'FromCourse':courseOptions[0].name
    } //this is used to define the default value when creating the form
    const config = {
    startDate: "2022-05-02",
    viewType:"WorkWeek",
    headerDateFormat: "dddd",
    dayBeginsHour: 9,
    dayEndsHour: 18,
    events:student.course.map((course)=>generateCourseEvent(course)).concat(student.activities.map((activity)=>generateActivityEvent(activity))),
    timeRangeSelectedHandling: "Enabled",
    onTimeRangeSelected: async (args) => {
    
    DayPilot.Modal.form(form,data).then((resultObj)=>{
        const dp = args.control;
        if(resultObj.canceled) { //If user clicked the cancel button
            dp.clearSelection()
            return //directly return 
        }
        const {result} = resultObj
        dp.clearSelection();
        const id = student.activities.length ===0 ? 1: student.activities[student.activities.length-1] + 1
        dp.events.add({
        start: args.start,
        end: args.end,
        id,
        text: result.CreateActivity + '\n' + result.FromCourse,
        clickDisabled:true,
        moveDisabled:true,
        resizeDisabled:true
    });
    //update global student object
        //create new id for the new activity
        student.activities.push({
            type:result.CreateActivity,
            startTime:args.start.toString(), 
            endTime:args.end.toString(),
            course:result.FromCourse,
            id
        })
    })
  },
  eventDeleteHandling: "Update",
  onEventDeleted: async (args) => { //event handler after deleted event from timetable
    const {data} = args.e //get the deleted event object from args parameter
    student.activities.forEach((activity,index)=>{
        if(activity.id === data.id) return student.activities.splice(index,1)
    })//remove the activity from the table
  }
    }
    return config
} 

export {TimetableConfig}