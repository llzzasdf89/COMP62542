import React,{Component} from 'react'
import './Status.css'
import {request} from '../../util'
import {Card,Button,Modal} from 'antd'
import { useOutletContext } from 'react-router-dom'
const {Meta} = Card
class Status extends Component{
    handleClick = () =>{
        const {student} = this.state
        if(student.status === 'notRegistered') {
            student.status  = 'pending'
            const params = {
                'registration':[student.uniNum]
            }
            request(params).then((resolved)=>{
                if(resolved === 'true'){ //means the status is changed successfully
                    return student.status ='pending'
                }
                return Modal.error({
                    content:'The server is busy at the moment, please try again later'
                })
            },()=>{
                return Modal.error({
                    content:'The server is busy at the moment, please try again later'
                })
            })
            this.setState({student})
        }
        else if(student.status === 'pending') {
            const params = {
                'pay':[student.uniNum]
            }
            request(params).then((resolved)=>{
                if(resolved === 'true'){ //means the status is changed successfully
                    return student.status ='registered'
                }
                return Modal.error({
                    content:'The server is busy at the moment, please try again later'
                })
            },(reject)=>{//means the request is in error
                return Modal.error({
                    content:'The server is busy at the moment, please try again later'
                })
            })
            student.status  = 'registered'
            this.setState({student})
        }
    }
    constructor(props){
        super(props)
        const {student} = props
        const reminders = student.student.reminders;
        this.state = student
        //set reminder if there is 
        if(reminders && reminders.length > 0) this.reminder = reminders[reminders.length -1].content;
        else this.reminder = ''
    }
    render(){
        const statusTemplate = {
            registered:{
                title:<div>
                <span style={{'fontWeight':150}}>Your status is </span> 
                <span style={{'textDecoration':'underline'}}>Registered</span>
                </div>,
                components:null
            },
            notRegistered:{
                title:<div>
                        <span style={{'fontWeight':150}}>Your status is </span> 
                        <span style={{'textDecoration':'underline'}}>Unregistered</span>
                        </div>,
                components:
                <div style={{lineHeight:'2rem'}}>
                        <p>Now you may want to </p>
                        <Button type='primary' shape='round' onClick={this.handleClick}>register</Button>
                </div>
            },
            pending:{
                title:<div>
                <span style={{'fontWeight':150}}>Your status is </span> 
                <span style={{'textDecoration':'underline'}}>Pending</span>
                </div>,
                components:
                <div>
                    <p>{this.reminder}</p>
                    <p>Now you may want to </p>
                    <Button type='primary' shape='round' onClick={this.handleClick}>pay the fees</Button>
                </div>
            }
        }
        const {status} = this.state.student
        return <Card title='Status information' className='statusContainer'>
            <Meta title = {statusTemplate[status].title} description={statusTemplate[status].components}></Meta>
        </Card>
    }
}
export default ()=> <Status student = {useOutletContext()}></Status>