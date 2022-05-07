import React,{Component} from 'react'
import { useOutletContext } from 'react-router-dom'
import NotRegisteredTemplate from '../NotRegisteredTemplate'
import {List, Button,Modal} from 'antd'
import './Newsletter.css'
import {request} from '../../util'
class Newsletter extends Component{
    constructor(props){
        super(props)
        const {student} = props.student
        //loadings array, used for control the icon beside the list item
        const iconArr = student.allNewsletters.map((item)=>{
            const isSubscribed = student.newsletters.some((i)=>i.id===item.id)
            if(isSubscribed){
                return {
                    text:'unsubscribe',
                    isGhost:true,
                    isLoading:false
                }
            }
            else {
                return {
                    text:'subscribe',
                    isGhost:false,
                    isLoading:false
                }
            }
        }) //generate the same numbers with the newsletter
        this.state = {
            student,
            iconArr //control the display of button in the UI
        }
    }
    handleClick = (index) =>{
        //When click the submit button, exchange the loading status
        const {iconArr,student} = this.state
        iconArr[index].isLoading =  true
        this.setState(iconArr)
        //exchange the status of the button
        if(!iconArr[index].isGhost) {
            //this branch shows that student needs to subscribe the newsletter
            const data = {
                subscribeNewsletter:[student.uniNum,student.allNewsletters[index].id]
            }
            request(data).then((resolve)=>{
                if(resolve === 'true'){//request success
                    student.newsletters.push(student.allNewsletters[index])
                    setTimeout(()=>{
                        iconArr[index].text = 'unsubscribe'
                        iconArr[index].isLoading = false
                        iconArr[index].isGhost = true
                        return this.setState({
                            iconArr,
                            student
                        }); 
                    },1500)
                }
                iconArr[index].text = 'subscribe'
                iconArr[index].isGhost = false
                iconArr[index].isLoading = false
            },(reject)=>{
                iconArr[index].text = 'subscribe'
                iconArr[index].isGhost = false
                iconArr[index].isLoading = false
                return Modal.error({
                    content:'The server is busy on the moment, please try again later;'
                })
            })
        }
        else {
            //unsubscribe
            const {student} = this.state
            const data = {
                cancelNewsletter:[student.uniNum,student.allNewsletters[index].id]
            }
            request(data).then((resolved)=>{
                if(resolved === 'true'){
                    const subscribedNewsletter = student.allNewsletters[index]
                    let indexInAllNewsletter = -1
                    student.allNewsletters.forEach((item,idx)=>{
                        if(item.id === subscribedNewsletter.id) indexInAllNewsletter = idx;
                    })
                    student.newsletters.splice(indexInAllNewsletter,1) //remove the subscribed newsletter from the list
                    setTimeout(()=>{
                        iconArr[index].text = 'subscribe'
                        iconArr[index].isLoading = false
                        iconArr[index].isGhost = false
                        return this.setState({
                            iconArr,
                            student
                        }); 
                    },1500)
                }
                iconArr[index].isLoading = false;
                iconArr[index].isGhost = true;
                iconArr[index].text = 'unsubscribe'
            },()=>{
                iconArr[index].isLoading = false;
                iconArr[index].isGhost = true;
                iconArr[index].text = 'unsubscribe'
                return Modal.error({
                    content:'Unsubscribe error, the server is busy now'
                })

            })
        }
        this.setState({student,iconArr}) //No matter whether the update operation success, updating the view.
    }
    render(){
        const {status} = this.state.student
        let template = <NotRegisteredTemplate></NotRegisteredTemplate>

        if(status === 'registered') {
            const {student} = this.state
            template = <List 
            bordered
            header = {<div>Newsletter</div>}
            dataSource={student.allNewsletters}
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
                <List.Item.Meta title={newsletter.content}> 
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