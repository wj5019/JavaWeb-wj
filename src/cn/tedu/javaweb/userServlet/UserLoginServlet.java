package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.pojo.User;

public class UserLoginServlet extends HttpServlet{
	//编写处理请求的service方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request请求的编码格式
		request.setCharacterEncoding("UTF-8");
		//设置response响应的编码格式
		response.setContentType("text/html;charset=UTF-8");
		//通过request对象，获取登录的用户名和密码
		//参数：getParameter("参数")，参数来自于页面，参数对应的是<input name="uname"/>
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		
		//调用dao层的方法判断是否存在该登录用户
		UserDao dao=new UserDao();
		User user=dao.selectByUnameAndUpwd(uname,upwd);
		if (user!=null) {//该用户可以登录
//			response.sendRedirect("index.html");
			request.getRequestDispatcher("allBookServlet").forward(request, response);
		}else {//该用户用户名或密码错误
			response.sendRedirect("login.html");
		}
		
//		//使用io流输出到浏览器的信息
//		PrintWriter out = response.getWriter();
//		out.println("<h3>恭喜你"+uname+"，登陆成功</h3>");
//		out.println("<6>您的密码是:"+upwd+"</6>");
//		out.close();
	}

}
