package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;

public class CheckPhoneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		  String phone=request.getParameter("phone");
		  System.out.println(phone);
		  UserDao dao=new UserDao();
		  PrintWriter writer=response.getWriter();
		  if (dao.ajaxCheckPhone(phone)) {
			  writer.write("yes");
		}else {
			 writer.close();
		}
	}
	

}
