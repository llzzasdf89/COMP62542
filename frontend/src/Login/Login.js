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
        if(input.length <= 1) {
            return Modal.error({
                content:<div><p>Please input a 1 digit number</p></div>
            })
        }
        const {navigate} = this
        const params = {
            login:[input]
        }
        request(params)
        navigate('/index',{state:this.state,replace:true})
    }
    switchToAdmission = ()=>{
        const {navigate} = this
        const params = {
            Admissioner:null
        }
        request(params).then((resolveObj)=>{
            this.setState(({
                Manager:{
                    reminder : [
                        {content:'your deadline is 2022-05-02'},
                        {content:'your deadline is 2022-05-03'}
                    ],
                    newsletter:[{
                        content:'test1'
                    }],
                    students:[
                        {name:'Richard'},
                        {name:'Test Student 1'},
                        {name:'Test Student 2'}
                    ],
                }
            }),()=>{
                navigate('/Admissioner',{state:this.state})
            })
        },(rejectObj)=>{
            this.setState(({
                Manager:{
                    reminder : [
                        {content:'your deadline is 2022-05-02'},
                        {content:'your deadline is 2022-05-03'}
                    ],
                    newsletter:[{
                        content:'test1'
                    }],
                    students:[
                        {
                            name:'Zhi Li',
                            studentID:'10846881',
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
                        },
                        {
                            name:'WX',
                            studentID:'1000086',
                            course:[]
                        },
                        {
                            name:'Test Student 2',
                            studentID:'10000087',
                            course:[
                                {
                                    title:'Querying Data on the Web',
                                    startTime:'2022-05-04T15:00:00',
                                    endTime:'2022-05-04T17:00:00',
                                    type:'Optional',
                                    department:"Mathematics"
                                }
                            ]
                        }
                    ],
                }
            }),()=>{
                navigate('/Admissioner',{state:this.state})
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
