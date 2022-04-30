
import React from 'react'
import Login from './Login/Login'
import Index from './Index/Index'
import Status from './Index/Status/Status'
import Course from './Index/Course/Course'
import Newsletter from './Index/Newsletter/Newsletter'
import Timetable from './Index/Timetable/Timetable'
import Home from './Index/Home/Home'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
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
            <Route index element = {<Home></Home>}></Route>
            <Route path = '/Index/Status' element = {<Status></Status>}></Route>
            <Route path = '/Index/Timetable' element = {<Timetable></Timetable>}></Route>
            <Route path = '/Index/Course' element = {<Course></Course>}></Route>
            <Route path = '/Index/Newsletter' element = {<Newsletter></Newsletter>}></Route>
        </Route>
    </Routes>
</BrowserRouter>
export default RouterMap