
import React from 'react'
import Login from './Login/Login'
import Index from './Index/Index'
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
            <Route path = '/Index/Status' element = {<Index></Index>}></Route>
            <Route path = '/Index/Timetable' element = {<Index></Index>}></Route>
            <Route path = '/Index/Course' element = {<Index></Index>}></Route>
            <Route path = '/Index/Newsletter' element = {<Index></Index>}></Route>
        </Route>
    </Routes>
</BrowserRouter>
export default RouterMap