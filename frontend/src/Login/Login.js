import React, {Component} from 'react'
import {Input,Button, Modal} from 'antd'
import {UserOutlined} from '@ant-design/icons'
import { useNavigate } from 'react-router-dom'
import './Login.css'

class Login extends Component {
    handleInput = e=>{
        //handle the input event
        let input = e.target.value;
        if(input.length >= 8 ) input = input.substr(0,8)
        this.setState({inputValue:input})
    }
    handleSubmit = e=>{
        //handle the submission event, validate the input from user
        const input = this.state.inputValue;
        if(input.length !== 8) {
            return Modal.error({
                content:<div><p>Please input a 8 digit number</p></div>
            })
        }
        /**
         * The place is preserved for API request
         */
        //mock data
        const student = {
            name:'RichardZhiLi',
            studentID:input,
            status:'unregistered',
            course:[
                {
                    title:'Software Engineering',
                    startTime:"2022-05-02T09:00:00",
                    endTime:'2022-05-02T11:00:00',
                    type:'Mandatory'
                },
                {
                    title:'Querying Data on the Web',
                    startTime:'2022-05-04T15:00:00',
                    endTime:'2022-05-04T17:00:00',
                    type:'Optional',
                    department:"Mathematics"
                },
                {
                    title:'Modelling data on the web',
                    startTime:'2022-05-05T15:00:00',
                    endTime:'2022-05-05T17:00:00',
                    type:'Optional Available',
                    department:"Computer Science"
                }
            ],
            activities:[
                {
                    type:'tutorial',
                    startTime:"2022-05-02T09:00:00",
                    endTime:'2022-05-02T11:00:00'
                },
                {
                    type:'supervision meeting',
                    startTime:"2022-05-02T09:00:00",
                    endTime:'2022-05-02T11:00:00'
                }
            ],
            newsletter:[{
                    title:'1111',
                    content:'HHHHH'
            }]
        }
        this.setState({student},()=>{
            const {navigate,state} = this
            navigate('/index',{replace:true,state}) //through the navigation function to pass communication with other component
        }) //setState is a asynchronous function, if we need to pass the data to the next page, we need to write it in the callback function
    }
    constructor(props){
        super(props)
        //bind the navigation method to the global instance 
        this.navigate = props.navigate
        this.state = {
            inputValue:'',
            student:null
        }
    }
    render(){
        const layout = 
        <div className='layout'>
            <div className='loginContainer'>
        <div className='loginContainer-inputArea'>
        <Input 
        placeholder='Input your unique number here' 
        type='number'
        value={this.state.inputValue}
        className='loginInput'
        size='large'
        onInput={this.handleInput}
        prefix={<UserOutlined></UserOutlined>}
        >
        </Input>
        </div>
        <div className='loginContainer-ButtonArea'>
        <Button 
        type ='primary' 
        shape="round" 
        size='large' 
        block 
        ghost
        onClick={this.handleSubmit}
        >
        Submit
        </Button>
        <Button 
        block 
        type='primary' 
        shape='round'
        href='/Admissioner'
        >
        Click here if you are admissioner
        </Button>
        </div>
            </div>
        </div>
        return layout
    }
}
export default ()=><Login navigate={useNavigate()}></Login>
/**
     * In react-router, useNavigate is a hook function, 
     *      if we wanna use it, then we need a wrapper function outside of the class
* */
