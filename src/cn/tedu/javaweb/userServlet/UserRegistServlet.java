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
		//����request����ı����ʽ
		request.setCharacterEncoding("UTF-8");
		//����response��Ӧ�ı����ʽ
		response.setContentType("text/html;charset=UTF-8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		UserDao dao=new UserDao();
		//����insert��������ע����Ϣ�������ݿ�
		dao.insert(phone, uname, upwd, email, 0);
		//ʹ���ض���
		response.sendRedirect("login.html");
		
		
		
		
		
		
		
		
		
		//ʹ��io����������������Ϣ
//				PrintWriter out = response.getWriter();
//				out.println("<h3>��ϲ��"+uname+"����½�ɹ�</h3>");
//				out.println("<h6>����������:"+upwd+"</h6>");
//				out.println("<h5>����������:"+email+"</h5>");
//				out.println("<h6>�����ֻ���:"+phone+"</h6>");
//				out.close();
		
	}

}
