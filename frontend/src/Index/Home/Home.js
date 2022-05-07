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
        const {student} = this.state
        return <div className="homeContainer">
                <div className='homeContainer-header'></div>
                Welcome {student.name}
                <br/> 
                <div className='homeContainer-body'>
                Student ID: {student.uniNum}
                </div>
                
                </div>
    }
}

export default (props)=> {
    //through the Outlet context hook function to get the params from parent component
    return <Home student = {useOutletContext()}></Home>
}