import React,{Component} from 'react'
import './Status.css'
const status = {
    registered:{
        title:'registered',
        components:null
    },
    unregisterd:{
        title:'unregistered',
        components:<div>Click to register</div>
    },
    pending:{
        title:'pending',
        components:<div>Click to pay the fees</div>
    }
}
class Status extends Component{
    render(){
        return <div className='statusContainer'>
        <div className='statusContainer-header'>
            Your status is: {status.unregisterd.title}
        </div>
        <div className='statusContainer-body'>
            {status.unregisterd.components}
        </div>
        <div className='statusContainer-footer'>
            this is footer
        </div>
        </div>
    }
}
export default Status