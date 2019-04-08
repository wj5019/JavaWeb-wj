package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1.继承HttpServlet，该API是由servlet组件提供的
public class HelloServlet extends HttpServlet{
	//重写父类中的方法，service()方法
	//service  alt+/
	//处理所有请求的服务入口
	@Override//重写
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理请求的代码逻辑由程序员编写
		//request专门处理请求
		//response负责响应给浏览器结果
		//功能：前台发送了一个hello请求
		//功能：后台需要处理这个请求
		//设置编码格式
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>欢迎来到峡谷</h3>");
		out.close();

	}

}
