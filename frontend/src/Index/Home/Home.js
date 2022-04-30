import React,{Component} from 'react'
import './Home.css'
import {useOutletContext} from 'react-router-dom'

class Home extends Component{
    constructor(props){
        super(props)
        const {student} = props
        this.state = student
    }
    render(){
        return <div>Welcome <br/> Student ID: {this.state.studentID}</div>
    }
}

export default (props)=> {
    //through the Outlet context hook function to get the params from parent component
    return <Home student = {useOutletContext()}></Home>
}