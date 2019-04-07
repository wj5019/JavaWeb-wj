package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;

public class UserRegistServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request请求的编码格式
		request.setCharacterEncoding("UTF-8");
		//设置response响应的编码格式
		response.setContentType("text/html;charset=UTF-8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		UserDao dao=new UserDao();
		//调用insert方法，把注册信息插入数据库
		dao.insert(phone, uname, upwd, email, 0);
		//使用重定向
		response.sendRedirect("login.html");
		
		
		
		
		
		
		
		
		
		//使用io流输出到浏览器的信息
//				PrintWriter out = response.getWriter();
//				out.println("<h3>恭喜你"+uname+"，登陆成功</h3>");
//				out.println("<h6>您的密码是:"+upwd+"</h6>");
//				out.println("<h5>您的邮箱是:"+email+"</h5>");
//				out.println("<h6>您的手机是:"+phone+"</h6>");
//				out.close();
		
	}

}
