//config of the Timetable, including the how many days to display, the time range...etc.
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
const TimetableConfig = (student)=>{
    console.log(student)
    const config = {
    startDate: "2022-05-02",
    viewType:"WorkWeek",
    headerDateFormat: "dddd",
    dayBeginsHour: 9,
    dayEndsHour: 18,
    events:student.courses.map((course)=>generateCourseEvent(course)),
    timeRangeSelectedHandling: "Disabled"
    }
    return config
} 

export {TimetableConfig}