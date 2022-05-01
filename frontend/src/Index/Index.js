/**
 * Index page, 
 * notice this Index.js is not the same file with the index.js in src/
 */
import React, {Component } from 'react'
import {Layout,Menu, Breadcrumb } from 'antd';
import {Link,useLocation,Outlet, Navigate} from 'react-router-dom';
import './Index.css'
const {Header,Content,Footer} = Layout
//Define what the menu item contains 
const menuItem = [
    {label:<Link to='/Index/Home'>Home</Link>,key:'Home', path:'/Index/Home'},
    {label:<Link to='/Index/Status'>Status</Link>, key:'Status',path:'/Index/Status'},
    {label:<Link to='/Index/Timetable'>Timetable</Link>,key:'Timetable',path:'/Index/Timetable'},
    {label:<Link to='/Index/Course'>Course</Link>,key:'Course',path:'/Index/Course'},
    {label:<Link to='/Index/Newsletter'>Newsletter</Link>,key:'Newsletter',path:'/Index/Newsletter'}
]
const routeNameMap = new Map()
/**
 *  According to the route, return the route path mapping to name;
 *  For example: when the route is /index, our navigation shows /Home */
menuItem.forEach((item)=>routeNameMap.set(item.path,'/' + item.key))
//function to judge whether the page user is accessing should be redirected
const shouldRedirect = (pathname)=>{
    const redirectRules = ['/index','/index/'] 
    //generally we only need to redirect the page when user access '/index' page
    return redirectRules.includes(pathname.toLowerCase())
}
class Index extends Component{
    constructor(props){
        super(props)
        //receive the input from Login component, including currentPath
        const {location} = props
        const {inputValue} = location.state || '10086' //default value, preventing the value is empty
        const {pathname} = location
        this.shouldRedirect = shouldRedirect(pathname)
        this.state = {studentID: inputValue} //bind it to the state object of React
        this.currentRoute =routeNameMap.get(pathname)
    }
    shouldComponentUpdate(nextProps){
        //React lifecycle function, automatically called when component is updating
        const pathname = nextProps.location.pathname
        this.shouldRedirect = shouldRedirect(pathname)
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
        {this.shouldRedirect && <Navigate replace to ='/Index/Home'></Navigate>}
        {/**Redirect component, if user try to access '/Index', redirect him to the '/Index/Home' */}
            <Header className='headerContainer'>
                <Menu
                theme="dark"
                mode="horizontal"
                className='header-menu'
                items={menuItem}
                defaultSelectedKeys = {[menuItem[0].key]}
                /
                >
            </Header>
            <Content className='contentContainer'>
                <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item key={this.currentRoute}>{this.currentRoute}</Breadcrumb.Item>
                </Breadcrumb>
                <div className="contentContainer-contentBox">
                <Outlet context = {this.state}></Outlet> {/*Outlet is a placeholder for children component */}

                </div>
            </Content>
            <Footer className='footerContainer'>COMP62542 Group Project by Group 8</Footer>
        </Layout>
        return layout
    }
}

export default ()=>//as usual, useLocation is a hook function and class could not directly use it
    <Index location={useLocation()}></Index>