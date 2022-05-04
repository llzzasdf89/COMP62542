import React,{Component} from 'react'
import {List, Divider,Card, Input, Button,Modal} from 'antd'
import './StudentUnion.css'
const {TextArea} = Input
const newsletter = [{
    content:'test1'
}]
for(let i = 0; i < 10; i++) {
    const tmp = {
        content:"test" + (i+2)
    }
    newsletter.push(tmp)
}
class StudentUnion extends Component{
    constructor(props){
        super(props)
        this.state = {
            newsletter,
            newsletterEditArr:newsletter.map((item)=> false),
            contentArea:''
        }
    }
    deleteNewsletter = (index)=>{
        const {newsletter} = this.state
        newsletter.splice(index,1)
        this.setState({
            newsletter
        })
    }
    inputContent = (e)=>{
        let {contentArea} = this.state
        contentArea = e.target.value
        this.setState({
            contentArea
        })
    }
    publishNewsletter = ()=>{
        const {contentArea,newsletter,newsletterEditArr} = this.state
        if(contentArea.length === 0) return Modal.error({
            content:<div><p>At least there should be content</p></div>
        })
        newsletter.push({
            content:contentArea
        })
        newsletterEditArr.push(false)
        Modal.success({
            content:<div><p>Publish newsletter success</p></div>
        })
        this.setState({
            newsletter,
            newsletterEditArr
        })
    }
    EditNewsletter = (index,e)=>{
        const {newsletter} = this.state
        newsletter[index].content = e.target.value
        this.setState({
            newsletter
        })
    }
    enableEditNewsletter = (index) =>{
        const {newsletterEditArr} = this.state
        newsletterEditArr[index] = !newsletterEditArr[index]
        this.setState({
            newsletterEditArr
        })
    }
    sendList = e=>{
        const {newsletter} = this.state
        return newsletter
    }
    render(){
        return <div className="studentunionContainer">
            <div className="leftArea" style={{overflow:'scroll'}}>
                <List 
                dataSource = {this.state.newsletter}
                
                header = {<div>Newsletter List</div>}
                footer = {
                <Button type='primary' 
                block 
                shape='round' 
                onClick={this.sendList}
                >Confirm</Button>}
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

export default ()=><StudentUnion></StudentUnion>

