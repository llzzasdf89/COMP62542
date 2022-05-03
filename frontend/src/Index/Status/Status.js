import React,{Component} from 'react'
import './Status.css'
import {Card,Button} from 'antd'
import { useOutletContext } from 'react-router-dom'
const {Meta} = Card
class Status extends Component{
    handleClick = () =>{
        const {student} = this.state
        if(student.status === 'unregistered') {
            student.status  = 'pending'
            this.setState({student})
        }
        else if(student.status === 'pending') {
            student.status  = 'registered'
            this.setState({student})
        }
    }
    constructor(props){
        super(props)
        const {student} = props
        this.reminder = student.reminder
        this.state = student
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
            unregistered:{
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
                    <p>{this.state.student.reminder}</p>
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