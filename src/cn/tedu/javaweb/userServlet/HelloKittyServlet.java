package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloKittyServlet extends HttpServlet{
	//��������ķ�����ڣ�service����
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //����response��Ӧ�ı����ʽ
	response.setContentType("text/html;charset=UTF-8");
	//ʹ���������������е���Ϣ
	PrintWriter out = response.getWriter();
	out.println("<h3>������<h3>");
	out.close();
	}

}