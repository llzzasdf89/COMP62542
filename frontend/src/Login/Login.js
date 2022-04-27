import React, {Component} from 'react'
import {Input,Button} from 'antd'
import './Login.css'
class Login extends Component {
    inputUniNum = e=>{
        let input = e.target.value;
        if(input.length >= 8 ) input = input.substr(0,8)
        this.setState({inputValue:input})
    }
    constructor(props){
        super(props)
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
        onInput={this.inputUniNum}
        >
        </Input>
        <Button shape="round" size='large' block>Submit</Button>
            </div>
        </div>
        return layout
    }
}
export default Login