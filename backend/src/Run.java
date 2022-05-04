import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Run {
    public static void main(String[] args) {
        	try {
        		//启动服务器的主要方法是通过引入HttpServer这个类，这个类在com.sun.net包下
				HttpServer server = HttpServer.create(new InetSocketAddress(8001),0);
				System.out.println("Running the server on the port 8001");//假设我们的服务器运行在8001端口
				//createContext API 是HttpServer类的一个关键API，它主要负责处理的是路由
				//下面这句话的意思是要监听/test这个路由下的请求，实施监听的对象是这个叫做TestHandler的对象
				server.createContext("/test",new TestHandler());
				server.start();//启动服务器的指令
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
    
    /**
     * 
     * @author lizhi
     * 看到这你可能已经心里面有个数了，起服务器不是很困难的一件事
     * 起了服务器以后要做的事才是后端所关心的事，那就是如何根据前端的需求来发送所需要的数据
     * 根据需求发送数据的这个过程其实就是写API的过程。而上文提到的Handler其实就是API，这个handler会监听客户端访问/test路由下的请求
     * 因此写API的关键所在就是写这些Handler，他们负责监听某个路由下的请求，然后执行内部的函数处理数据，最后输出该数据给访问者
     * 例如我在上面使用了createContext方法绑定了的路由叫做/test, 而这个/test对应的Handler是下面我所重写的名叫TestHandler
     * 这个Handler会监听访问/test的请求，然后返回一个JSON字符串"{"weight":"23400"}"
     * 
     */
    static class TestHandler implements HttpHandler{
    	//注意重写Handler的时候最主要的事情就是重写这个Handle方法
        @Override
        public void handle(HttpExchange exchange) throws IOException {
        	//请求的内容、参数等等都是从这个HttpExchange对象中获取,详情请自己查阅文档，我这里为了举例方便就不详细说明了

        	final String json = "{\"weight\":\"23400\"}"; //定义这个API需要输出的数据，假设我们就以一个字符串为例子
        	/**
        	 * 设置响应头，响应头是HTTP报文的一个重要部分，它定义了HTTP报文该以什么样的格式和数据类型返回给前端
        	 */
        	//前两行是关于跨域的响应头的设置, 关于什么是跨域你们有兴趣就自个了解一下就好了，这个是前端访问后端的一个限制策略，后端如果不这么设置前端无法访问
            final String origin = exchange.getRequestHeaders().getFirst("Origin");
            if(origin != null) exchange.getResponseHeaders().add("Access-Control-Allow-Origin", origin);
            //第三行第四行是响应头里面比较重要的两个filed，分别控制请求允许的方法还有响应头的字段
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");//最后两行就是关于设置响应的数据格式类型，这里我们假设全部都返回JSON，所以响应头这里就设置contentType:JSON就行了
            exchange.sendResponseHeaders(200, json.getBytes().length);//然后是设置响应的代码，默认我们为200，第二个参数是这个响应的数据的长度
            try(OutputStream os = exchange.getResponseBody()) {//请求头发送完后就是发送数据内容了,使用IO对象进行发送
                os.write(json.getBytes());
                os.flush();
            }
            exchange.close(); //该次数据发送结束，关闭该API
            //因此当我的应用程序或者我用浏览器访问这个地址localhost:8081/test的时候，我就会得到一个"{\"weight\":\"23400\"}"字符串的响应
            //看到这你大概就明白了，这个API其实没有干任何事情，就是单纯的当有人访问/test的时候输出字符串"{\"weight\":\"23400\"}"的API
            //其余的API就需要你们自己来写了，比如我想要学生的名字，这个学生名字的API所做的事情，就是在这个函数里面首先查询数据库，然后将查询到的结果输出
        }
    }
    }
