/**
 * Index page, 
 * notice this Index.js is not the same file with the index.js in src/
 */
import React, {Component } from 'react'
import {Layout,Menu, Breadcrumb } from 'antd';
import {Link,useLocation } from 'react-router-dom';
import './Index.css'
const {Header,Content,Footer} = Layout
//Define what the menu item contains 
const menuItem = [
    {label:<Link to='/Index'>Home</Link>,key:'Home', path:'/Index'},
    {label:<Link to='/Index/Status'>Status</Link>, key:'Status',path:'/Index/Status'},
    {label:<Link to='/Index/Timetable'>Timetable</Link>,key:'Timetable',path:'/Index/Timetable'},
    {label:<Link to='/Index/Course'>Course</Link>,key:'Course',path:'/Index/Course'},
    {label:<Link to='/Index/Newsletter'>Newsletter</Link>,key:'Newsletter',path:'/Index/Newsletter'}
]
const routeNameMap = new Map()
//According to the route, return the route path mapping to name;
//For example: when the route is /index, our navigation shows /Home
menuItem.forEach((item,index)=>{
    if(index===0) routeNameMap.set(item.path,item.key)
    const res = item.path.split('/')
    res[1] = menuItem[0].key
    routeNameMap.set(item.path,res.join('/'))
})
class Index extends Component{
    constructor(props){
        super(props)
        const pathname = props.location.pathname
        this.currentRoute =routeNameMap.get(pathname)
    }
    shouldComponentUpdate(nextProps){
        //React lifecycle function, automatically called when component is updating
        const pathname = nextProps.location.pathname
        this.currentRoute = routeNameMap.get(pathname)
        return true
    }
    render(){
        /**
         * The layout of index page follows the typical layout;
         * Top - Body - Foot, three rows of layout
         */
        const layout = 
        <Layout className='layoutContainer'>
            <Header className='headerContainer'>
                <Menu
                theme="dark"
                mode="horizontal"
                className='header-menu'
                items={menuItem}/
                >
            </Header>
            <Content className='contentContainer'>
                <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item key={this.currentRoute}>{this.currentRoute}</Breadcrumb.Item>
                </Breadcrumb>
                <div className="contentContainer-contentBox">Content</div>
            </Content>
            <Footer className='footerContainer'>COMP62542 Group Project by Group 8</Footer>
        </Layout>
        return layout
    }
}

export default ()=>{
    //as usual, useLocation is a hook function and class could not directly use it
    const location = useLocation();
    return <Index location={location}></Index>
}