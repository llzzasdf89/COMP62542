import React,{Component} from 'react'
import {Input, Card,Button,Modal, Divider, List} from 'antd'
import './AdmissionOffice.css'
import StudentUnion from '../StudentUnion/StudentUnion'
const {TextArea} = Input
class AdmissionOffice extends Component{

   handleSubmit = ()=>{
       const {reminder,reminderContentArea,reminderEditArr} = this.state
        if(reminderContentArea.length === 0) return Modal.error({
            content:<div><p>At least you need to input reminder</p></div>
        })
        reminder.push({
            content:reminderContentArea
        }) //update the global reminder array according to the input of user
        Modal.success({
            content:<div><p>Send reminder success</p></div>
        })
        reminderEditArr.push(false)
        this.setState({
            reminder,
            reminderEditArr
        })
        return reminder
   }
   EditReminder = (index,e)=>{
        const {reminder} = this.state
        reminder[index].content = e.target.value
        this.setState({
            reminder
        })
   }
   enableEditReminder = (index)=>{
    const {reminderEditArr} = this.state
    reminderEditArr[index] = !reminderEditArr[index]
    this.setState({
        reminderEditArr
    })
   }
   deleteReminder = (index) => {
       const {reminderEditArr, reminder} = this.state
       reminderEditArr.splice(index,1)
       reminder.splice(index,1)
       this.setState({
           reminderEditArr,
           reminder
       })
   }
   constructor(props){
        super(props)
        const reminder = [
            {content:'your deadline is 2022-05-02'},
            {content:'your deadline is 2022-05-03'}
        ]
        this.state = {
            reminderContentArea:'',
            reminder,
            reminderEditArr:reminder.map(()=> false),
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
            header="Reminder List"
            dataSource={this.state.reminder}
            renderItem = {(item,index)=>
                <List.Item 
                actions={[
                <Button 
                type='primary' 
                shape='round' 
                onClick={() => this.deleteReminder(index)}
                >Delete</Button>, 
                
                <Button 
                type='primary' 
                shape='round' 
                ghost = {this.state.reminderEditArr[index]}
                onClick={()=>this.enableEditReminder(index)}
                >Edit</Button>]}>
                <Input 
                        value={item.content} 
                        bordered={this.state.reminderEditArr[index]} 
                        disabled = {!this.state.reminderEditArr[index]}
                        onInput = {(e)=>this.EditReminder(index,e)}
                        style = {{
                            overflow:'hidden',
                            textOverflow:'ellipsis',
                            whiteSpace:'nowrap'
                        }}
                        >
                        </Input>
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

export default ()=><AdmissionOffice></AdmissionOffice>

