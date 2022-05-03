import React, {Component } from 'react'
import {Result} from 'antd'
import './NotRegisteredTemplate.css'

class NotRegisteredTemplate extends Component {
    render(){
        return <Result title="Since you are not registered, you are not able to access this area" className='resultTemplate'></Result>
    }
}
export default NotRegisteredTemplate