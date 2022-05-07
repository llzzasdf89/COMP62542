/**
 * Because the list of Mandatory Courses and Optional Course are almost the same;
 * The only difference is that Mandatory Courses are not able to be editted, so the right panel does not show buttons;
 * Similarly, Optional course and Available Optional courses are almost equal, but one's button is 'add' while the other is 'delete'
 * So this component mainly aims at resolving this difference among three lists
 */
import React,{Component} from 'react'
import {List, Divider,Button} from 'antd'  

class ListComponent extends Component{
    render(){
        const {courseList,listTitle} = this.props
        //deconstructure the params from parent component. In this way, if parent compoent update the child component will update too
        return <div>
        <Divider orientation="center">{listTitle}</Divider>
        <List
          dataSource={courseList}
          renderItem={course => {
            let listItem = null
            //According to the title, generate different list item button
            if(listTitle === 'Your Mandatory Course') listItem = <List.Item><span style={{fontWeight:'bolder'}}>{course.name}</span></List.Item>
            else if (listTitle === 'Your Optional Course') {
              
              listItem = <List.Item 
              actions={[<Button 
              type='primary' 
              shape='round' 
              onClick={()=>this.props.handleClick(course,'delete')}>Delete</Button>]}>
              <List.Item.Meta
                title={course.name}
                description={course.department}
              />
              </List.Item>
              
              }
            else if (listTitle === 'Optional Courses/Activities Available') {
              listItem = <List.Item 
              actions={[<Button 
              type='primary' 
              shape='round' 
              onClick={()=>this.props.handleClick(course,'add')}>Add</Button>]}>
              <List.Item.Meta
                title={course.name}
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
