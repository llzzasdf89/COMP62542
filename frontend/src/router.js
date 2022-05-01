
import Login from './Login/Login'
import Index from './Index/Index'
import Status from './Index/Status/Status'
import Course from './Index/Course/Course'
import Newsletter from './Index/Newsletter/Newsletter'
import Timetable from './Index/Timetable/Timetable'
import Home from './Index/Home/Home'
import {BrowserRouter, Route, Routes, Navigate} from 'react-router-dom'
/**
 * Manage the router of all the pages using "react-router"
 * 'localhost:3000/' -> Login page;
 * 'localhost:3000/Index' -> Index page;
 */
const RouterMap = 
<BrowserRouter>
    <Routes>
        <Route path = '/' element = {<Login></Login>}></Route>
        <Route path = '/Index' element = {<Index></Index>}>
            <Route path= 'Home' element = {<Home></Home>}></Route>
            <Route path = 'Status' element = {<Status></Status>}></Route>
            <Route path = 'Timetable' element = {<Timetable></Timetable>}></Route>
            <Route path = 'Course' element = {<Course></Course>}></Route>
            <Route path = 'Newsletter' element = {<Newsletter></Newsletter>}></Route>
            ({/* Default route, if user navigate to anywhere else of the specific route, redirect him to the home page */})
            <Route path = '*' element = {<Navigate to='/Index/Home'></Navigate>}></Route>
        </Route>
    </Routes>
</BrowserRouter>
export default RouterMap