import React,{Component} from 'react'
import {List, Divider,Card, Input, Button,Modal} from 'antd'
import { useOutletContext } from 'react-router-dom'
import './StudentUnion.css'
import {request} from '../../util'
import { DownCircleFilled } from '@ant-design/icons'
import Newsletter from '../../Index/Newsletter/Newsletter'
const {TextArea} = Input
class StudentUnion extends Component{
    constructor(props){
        super(props)
        const {Manager} = props.params
        const {newsletters} = Manager
        this.state = {
            newsletters,
            newsletterEditArr:newsletters.map((item)=> false),
            contentArea:''
        }
    }
    //handler when user click the 'delete' button
    deleteNewsletter = (index)=>{
        const {newsletters} = this.state
        const params = {
            deleteNewsletter:[newsletters[index].id]
        }
        request(params).then((resolved)=>{
            if(resolved ==='true'){
                newsletters.splice(index,1)
                this.setState({
                    newsletters
                })
                return Modal.success({
                    content:'delete newsletter success'
                })
            }
            return Modal.error({
                content:'delete newsletter error, please check the server status'
            })
        },(reject)=>{
            return Modal.error({
                content:'delete newsletter error, please check the server status'
            })
        })
    }
    //listener of input event
    inputContent = (e)=>{
        let {contentArea} = this.state
        contentArea = e.target.value
        this.setState({
            contentArea
        })
    }
    //listener of input event
    publishNewsletter = ()=>{
        const {contentArea,newsletters,newsletterEditArr} = this.state
        if(contentArea.length === 0) return Modal.error({
            content:<div><p>At least there should be content</p></div>
        })
        let id = -1;
        if(newsletters.length === 0) id = 1;
        else  id = parseInt(newsletters[newsletters.length-1].id) + 1
        const params = {addNewsletter:[id,contentArea]}
        request(params).then((resolved)=>{
            if(resolved === 'true'){
                newsletters.push({
                    id,
                    content:contentArea
                })
                newsletterEditArr.push(false)
                this.setState({
                    newsletters,
                    newsletterEditArr
                })
                return Modal.success({
                    content:"add newletter sucess"
                })
            }
            return Modal.error({
                content:'Add newsletter failed, please check the server'
            })
            
        },()=>{
            return Modal.error({
                content:'Add newsletter failed, please check the server'
            })
        })
    }
    //listener when user editr the content inside the content area
    EditNewsletter = (index,e)=>{
        const {newsletters} = this.state
        const previousContent = newsletters[index].content
        newsletters[index].content = e.target.value
        if(newsletters[index].content.length < 1) newsletters[index].content = previousContent
        this.setState({
            newsletters
        })

    }
    //listener when user clicks the edit button
    enableEditNewsletter = (index) =>{
        const {newsletterEditArr,newsletters} = this.state
        if(newsletterEditArr[index]){ //when user disabled the edit button, send the request to backend
            const params = {
                updateNewsletter:[newsletters[index].id,newsletters[index].content]
            }
            if(newsletters[index].content.length === 0){
                newsletterEditArr[index] = !newsletterEditArr[index]
                this.setState({
                    newsletterEditArr,
                    newsletters
                })
                return Modal.error({
                    content:'Please input some edited content'
                })
            }
           request(params).then((resolved)=>{
                if(resolved === 'true') {
                    newsletters[index].content = newsletters[index].content
                    return Modal.success({
                        content:'edit newsletter success'
                    })
                }
                return Modal.error({
                    content:'Edit error, please check the server status'
                })
           },(reject)=>{
                return Modal.error({
                    content:'Edit error, please check the server status;'
                })
           })
        }
        newsletterEditArr[index] = !newsletterEditArr[index]
        this.setState({
            newsletters,
            newsletterEditArr
        })
    }
    render(){
        return <div className="studentunionContainer">
            <div className="leftArea" style={{overflow:'scroll'}}>
                <List 
                dataSource = {this.state.newsletters}
                
                header = {<div>Newsletter List</div>}
                renderItem = {
                    (item,index)=> <List.Item
                    actions={[<Button 
                    type='primary' 
                    shape='round' 
                    onClick={() => this.deleteNewsletter(index)}>Delete</Button>, 
                    <Button 
                    type='primary' 
                    shape='round' 
                    onClick={()=>this.enableEditNewsletter(index)}
                    ghost = {this.state.newsletterEditArr[index]}
                    >Edit</Button>]}
                    >
                        <Input 
                        value={item.content} 
                        bordered={this.state.newsletterEditArr[index]} 
                        disabled = {!this.state.newsletterEditArr[index]}
                        onInput = {(e)=>this.EditNewsletter(index,e)}
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
            <div>
            <Divider orientation='center' 
            type='vertical' 
            style={{height:'100%'}}
            >

            </Divider></div>
            <div className='rightArea'>
                <Card title='publish a newsletter' style={{height:'100%'}}>
                    <TextArea autoSize={{minRows:5}} value={this.state.contentArea} onInput = {this.inputContent} placeholder="content of newsletter"></TextArea>
                    <Button type='primary' shape='round' block onClick={this.publishNewsletter}>Submit</Button>
                </Card>
            </div>
        </div>
    }
}

export default ()=><StudentUnion params = {useOutletContext()}></StudentUnion>

