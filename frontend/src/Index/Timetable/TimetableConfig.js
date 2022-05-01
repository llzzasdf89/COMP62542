//config of the Timetable, including the how many days to display, the time range...etc.
import {DayPilot} from "daypilot-pro-react";
const generateCourse = (course)=>{
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
 //This is courses' mock data, these courses are not able to be deleted by the user
const mockCourses = [
    {
        title:'Software Engineering',
        startTime:"2022-05-02T09:00:00",
        endTime:'2022-05-02T11:00:00'
},
    {
        title:'Modelling Data on the Web',
        startTime:"2022-05-03T13:00:00",
        endTime:'2022-05-03T15:00:00'
    }
]
const courses = mockCourses.map((item) => generateCourse(item))
const TimetableConfig = {
    startDate: "2022-05-02",
    viewType:"WorkWeek",
    headerDateFormat: "dddd",
    dayBeginsHour: 9,
    dayEndsHour: 18,
    events:courses,
    timeRangeSelectedHandling: "Enabled",
    onTimeRangeSelected: async (args) => {
    const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
    const dp = args.control;
    dp.clearSelection();
    if (modal.canceled) return
    dp.events.add({
      start: args.start,
      end: args.end,
      id: DayPilot.guid(),
      text: modal.result
    });
  },
  eventDeleteHandling: "Update",
  eventMoveHandling: "Update",
  eventResizeHandling: "Update",
  eventClickHandling: "Edit",
  eventEditHandling: "Update"
} 

export default TimetableConfig