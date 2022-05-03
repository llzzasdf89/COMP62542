/**
 * Because the list of Mandatory Courses and Optional Course are almost the same;
 * The only difference is that Mandatory Courses are not able to be editted, so the right panel does not show buttons;
 * Similarly, Optional course and Available Optional courses are almost equal, but one's button is 'add' while the other is 'delete'
 * So this component mainly aims at resolving this difference among three lists
 */
import React,{Component} from 'react'
import {List, Divider,Button} from 'antd'  

class ListComponent extends Component{
    constructor(props){
        //receive the params from parent component 'Course'
        super(props)
        const {handleClick} = props
        this.state = {...props}
        this.handleClick = handleClick
    }
    render(){
        const {courseList,listTitle} = this.state
        //deconstructure the params from state object
        return <div>
        <Divider orientation="center">{listTitle}</Divider>
        <List
          dataSource={courseList}
          renderItem={course => {
            let listItem = null
            //According to the title, generate different list item button
            if(listTitle === 'Your Mandatory Course') listItem = <List.Item><span style={{fontWeight:'bolder'}}>{course.title}</span></List.Item>
            else if (listTitle === 'Your Optional Course') {
              
              listItem = <List.Item 
              actions={[<Button 
              type='primary' 
              shape='round' 
              onClick={()=>this.handleClick(course,'delete')}>Delete</Button>]}>
              <List.Item.Meta
                title={course.title}
                description={course.department}
              />
              </List.Item>
              
              }
            else if (listTitle === 'Optional Courses Available') {
              listItem = <List.Item 
              actions={[<Button 
              type='primary' 
              shape='round' 
              onClick={()=>this.handleClick(course,'add')}>Add</Button>]}>
              <List.Item.Meta
                title={course.title}
                description={course.department}
              />
              </List.Item>
            }
            return listItem
          }
          }
          bordered
        />
        </div>
    }
}

export default ListComponent
