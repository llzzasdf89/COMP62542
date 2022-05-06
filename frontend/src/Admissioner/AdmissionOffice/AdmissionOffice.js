import React,{Component} from 'react'
import {Input, Card,Button,Modal, Divider, List} from 'antd'
import { useOutletContext } from 'react-router-dom'
import './AdmissionOffice.css'
import StudentUnion from '../StudentUnion/StudentUnion'
import {request} from '../../util'
const {TextArea} = Input
class AdmissionOffice extends Component{

   handleSubmit = ()=>{
       const {reminder,reminderContentArea} = this.state
        if(reminderContentArea.length === 0) return Modal.error({
            content:<div><p>At least you need to input reminder</p></div>
        })
        reminder.push({
            content:reminderContentArea
        }) //update the global reminder array according to the input of user
        Modal.success({
            content:<div><p>Send reminder success</p></div>
        })
        this.setState({
            reminder
        })
        return reminder
   }
   constructor(props){
        super(props)
        const {Manager} = props.params
        const reminder = Manager.reminder
        this.state = {
            reminderContentArea:'',
            reminder
    }
        }

    render(){
        return <div className='container'>
        <Card title="Edit newsletter" className="newsletterArea">
            <StudentUnion></StudentUnion>

        </Card>
        <Divider type="vertical" orientation='center' style={{height:'100%'}}></Divider>
        <Card className='reminderArea' title='Edit Reminder' >
            <div className='reminderArea-list'>
            <List  
            header={<div style={{fontWeight:'bolder'}}>Reminder List</div>}
            dataSource={this.state.reminder}
            renderItem = {(item)=>
                <List.Item>
                {item.content}
                </List.Item>
            }
            ></List>
            </div>
            <Card className='reminderArea-input' title="publish a reminder">
            <TextArea 
            placeholder='content of reminder'
            value = {this.state.reminderContentArea}
            onInput = {(e) => this.setState({reminderContentArea:e.target.value})}
            ></TextArea>
            <Button type='primary' block shape='round'  onClick={this.handleSubmit}>Submit</Button>
            </Card>
        </Card>
        </div>
    }
}

export default ()=><AdmissionOffice params = {useOutletContext()}></AdmissionOffice>

