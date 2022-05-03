import React,{Component} from 'react'
import { useOutletContext } from 'react-router-dom'
import NotRegisteredTemplate from '../NotRegisteredTemplate'
import {List, Button} from 'antd'
import './Newsletter.css'
class Newsletter extends Component{
    constructor(props){
        super(props)
        const {student} = props.student
        //loadings array, used for control the icon beside the list item
        const iconArr = student.newsletter.map((item)=>{
            return {
                text:'subscribe',
                isLoading:false,
                isGhost:false
            }
        }) //generate the same numbers with the newsletter
        this.state = {
            student,
            iconArr
        }
    }
    handleClick = (index) =>{
        //When click the submit button, exchange the loading status
        const {iconArr} = this.state
        iconArr[index].isLoading =  true
        this.setState(iconArr)
        //exchange the status of the button
        if(!iconArr[index].isGhost) {
            //this branch shows that student needs to subscribe the newsletter
            const {student} = this.state
            student.newsletter[index].subscribed = true
            setTimeout(()=>{ //if button is subscribe
                iconArr[index].text = 'unsubscribe'
                iconArr[index].isLoading = false
                iconArr[index].isGhost = true
                this.setState(iconArr)
            },3000)
            this.setState({student})
        }
        else {
            //unsubscribe
            const {student} = this.state
            student.newsletter[index].subscribed = false
            setTimeout(()=>{ //if button is unscribe
                iconArr[index].text = 'subscribe'
                iconArr[index].isLoading = false
                iconArr[index].isGhost = false
                this.setState(iconArr)
            })
            this.setState({student}) //update the data in the student
        }
    }
    render(){
        const {status} = this.state.student
        let template = <NotRegisteredTemplate></NotRegisteredTemplate>

        if(status === 'registered') {
            const {student} = this.state
            template = <List 
            bordered
            header = {<div>Newsletter</div>}
            dataSource={student.newsletter}
            renderItem = {
                (newsletter,index) => 
                <List.Item actions={[
                    <Button type='primary' 
                    shape='round' 
                    ghost = {this.state.iconArr[index].isGhost}
                    loading={this.state.iconArr[index].isLoading} 
                    onClick={()=>this.handleClick(index)}
                    > 
                    {this.state.iconArr[index].text}
                    
                    </Button>]}>
                <List.Item.Meta title={newsletter.title} description = {newsletter.content}> 
                <div>{newsletter.content}</div>
                </List.Item.Meta>
                </List.Item>
            }
            >
            </List>
        }
        return template
    }
}
export default ()=> <Newsletter student = {useOutletContext()}></Newsletter>