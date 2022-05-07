/**
 * Index page, 
 * notice this Index.js is not the same file with the index.js in src/
 */
import React, {Component } from 'react'
import {Layout,Menu, Breadcrumb } from 'antd';
import {Link,useLocation,Outlet, Navigate} from 'react-router-dom';
import './Index.css'
const {Header,Content,Footer} = Layout
//function to judge whether the page user is accessing should be redirected
const shouldRedirect = (pathname)=>{
    const redirectRules = ['/index','/index/','/admissioner','/admissioner/'] 
    //generally we only need to redirect the page when user access '/index' page
    return redirectRules.includes(pathname.toLowerCase())
}
const defaultStudent = {
    name:'RichardZhiLi',
    studentID:'1000086',
    status:'unregistered',
    course:[
        {
            title:'Software Engineering',
            startTime:"2022-05-02T09:00:00",
            endTime:'2022-05-02T11:00:00',
            type:'Mandatory'
        },
        {
            title:'Querying Data on the Web',
            startTime:'2022-05-04T15:00:00',
            endTime:'2022-05-04T17:00:00',
            type:'Optional',
            department:"Mathematics"
        },
        {
            title:'Modelling data on the web',
            startTime:'2022-05-05T15:00:00',
            endTime:'2022-05-05T17:00:00',
            type:'Optional Available',
            department:"Computer Science"
        }
    ],
    activities:[
        {
            id:1,
            type:'Tutorial',
            startTime:"2022-05-02T09:00:00",
            endTime:'2022-05-02T11:00:00',
            course:'Software Engineering'
        },
        {
            id:2,
            type:'Supervision Meeting',
            startTime:"2022-05-02T09:00:00",
            endTime:'2022-05-02T11:00:00',
            course:'Querying data on the Web'
        }
    ],
    newsletter:[{
            title:'1111',
            content:'HHHHH',
            subscribed:false
    }],
    reminder:'Your deadline of payment is 2022-05-09, please notice'
}
class Index extends Component{
    constructor(props){
        super(props)
        //receive the input from Login component, including currentPath
        const {location} = props
        const {pathname} = location
        //Define what the menu item contains according to the route
        const inIndexRoute = pathname.split('/')[1].toLowerCase()==='index'
        this.inIndexRoute = inIndexRoute
        const menuItem = inIndexRoute?[
            {label:<Link to='/Index/Home'>Home</Link>,key:'Home', path:'/Index/Home'},
            {label:<Link to='/Index/Status'>Status</Link>, key:'Status',path:'/Index/Status'},
            {label:<Link to='/Index/Timetable'>Timetable</Link>,key:'Timetable',path:'/Index/Timetable'},
            {label:<Link to='/Index/Course'>Course</Link>,key:'Course',path:'/Index/Course'},
            {label:<Link to='/Index/Newsletter'>Newsletter</Link>,key:'Newsletter',path:'/Index/Newsletter'}
        ]:[
            {label:<Link to='/Admissioner/AdmissionOffice'>AdmissionOffice</Link>,key:'AdmissionOffice', path:'/Admissioner/AdmissionOffice'},
            {label:<Link to='/Admissioner/SupportOffice'>SupportOffice</Link>, key:'SupportOffice',path:'/Admissioner/SupportOffice'},
            {label:<Link to='/Admissioner/StudentUnion'>StudentUnion</Link>,key:'StudentUnion',path:'/Admissioner/StudentUnion'},
        ]
        this.menuItem = menuItem
        const routeNameMap = new Map()
        /**
        *  According to the route, return the route path mapping to name;
        *  For example: when the route is /index, our navigation shows /Home */
        menuItem.forEach((item)=>routeNameMap.set(item.path,'/' + item.key))
        this.routeNameMap = routeNameMap
        let student = defaultStudent;//default value is defaultStudent, preventing the value is empty
        let Manager = null

        if(location.state.student) student= location.state.student
        if(location.state.Manager) Manager = location.state.Manager
        this.shouldRedirect = shouldRedirect(pathname)
        this.state = {student,Manager} //bind it to the state object of React
        console.log('Manager is ', Manager)
        this.currentRoute =routeNameMap.get(pathname)
    }
    shouldComponentUpdate(nextProps){
        //React lifecycle function, automatically called when component is updating
        const pathname = nextProps.location.pathname
        this.shouldRedirect = shouldRedirect(pathname)
        this.currentRoute = this.routeNameMap.get(pathname)
        return true
    }
    render(){
        /**
         * The layout of index page follows the typical layout;
         * Top - Body - Foot, three rows of layout
         */
        const layout = 
        <Layout className='layoutContainer'>
        {this.shouldRedirect && <Navigate replace to={this.inIndexRoute?'/Index/Home':'/Admissioner/AdmissionOffice'}></Navigate>}
        {/**Redirect component, if user try to access '/Index', redirect him to the '/Index/Home' */}
            <Header className='headerContainer'>
                <Menu
                theme="dark"
                mode="horizontal"
                className='header-menu'
                items={this.menuItem}
                defaultSelectedKeys = {[this.menuItem[0].key]}
                selectedKeys = {[this.currentRoute?this.currentRoute.substring(1):'Home']}
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