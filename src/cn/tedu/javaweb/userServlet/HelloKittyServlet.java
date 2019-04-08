package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloKittyServlet extends HttpServlet{
	//处理请求的服务入口：service方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //设置response响应的编码格式
	response.setContentType("text/html;charset=UTF-8");
	//使用流输出的浏览器中的信息
	PrintWriter out = response.getWriter();
	out.println("<h3>喵喵喵<h3>");
	out.close();
	}

}