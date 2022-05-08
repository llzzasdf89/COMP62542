import React, {Component} from 'react'
import {Input,Button, Modal} from 'antd'
import {UserOutlined} from '@ant-design/icons'
import { useNavigate } from 'react-router-dom'
import {request} from '../util'
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
        if(input.length < 1) {
            return Modal.error({
                content:<div><p>Please input a 1 digit number</p></div>
            })
        }
        const {navigate} = this
        const params = {
            login:[input]
        }
        request(params).then((data)=>{
            data = JSON.parse(data)
            this.setState({
                student:data
            },()=>{
                navigate('/Index',{
                    state:this.state
                })
            })
        },()=>{

        })
    }
    switchToAdmission = ()=>{
        //this function controls the display of admission view
        const {navigate} = this
        const params = {
            Admissioner:null
        }
        request(params).then((data)=>{
                data = JSON.parse(data) //request sucess, receive data from server
                this.setState({
                    Manager:data
                },()=>{
                    navigate('/Admissioner',{
                        state:this.state
                    })
                })
        },()=>{//request failed , directly return
           return Modal.error({
               content:"Communication with server error, please check server"
           })
        })

    }
    constructor(props){
        super(props)
        //bind the navigation method to the global instance 
        this.navigate = props.navigate
        this.state = {
            inputValue:''
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
        onClick={this.switchToAdmission}
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
