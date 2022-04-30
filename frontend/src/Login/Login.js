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
        this.setState({inputValue:input})
        const {navigate,state} = this
        navigate('/index',{replace:true,state}) //through the navigation function to pass communication with other component
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
