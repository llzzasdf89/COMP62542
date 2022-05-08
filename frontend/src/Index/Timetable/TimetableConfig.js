//config of the Timetable, including the how many days to display, the time range...etc.
//define the form options when creating the new event in the timetable
    const dayMap = new Map();
    dayMap.set('Mon','2022-05-02T')
    dayMap.set('Tue','2022-05-03T')
    dayMap.set('Wed','2022-05-04T')
    dayMap.set('Thu','2022-05-05T')
    dayMap.set('Fri','2022-05-06T')
const generateCourseEvent = (course)=>{
    /**
     * Because each course event in the timetable is immutable 
     * The only difference is that each course's time slot.
     * So this function is to quickly generate a template course event in the timetable
     * */
     let startTime
     let endTime
    if(course.time){
        startTime = dayMap.get(course.time) + course.startTime
        endTime = dayMap.get(course.time) + course.endTime
    }
    else {
        startTime = dayMap.get(course.day) + course.startTime
        endTime = dayMap.get(course.day) + course.endTime
    }
    const courseEvent = {
        id:course.courseNum,
        deleteDisabled:true,
        moveDisabled:true,
        doubleClickDisabled:true,
        resizeDisabled:true,
        clickDisabled:true,
        start:startTime,
        end:endTime,
        text:course.name
    }
    return courseEvent
}
const TimetableConfig = (student)=>{
    const courses = []
    student.courses.forEach((item)=>{
        if(Array.isArray(item) && item.length === 0) return;
        courses.push(item)
    })
    const config = {
    startDate: "2022-05-02",
    viewType:"WorkWeek",
    headerDateFormat: "dddd",
    dayBeginsHour: 9,
    dayEndsHour: 18,
    events:courses.map(item=>generateCourseEvent(item)),
    timeRangeSelectedHandling: "Disabled"
    }
    return config
} 

export {TimetableConfig}