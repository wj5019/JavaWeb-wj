package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1.�̳�HttpServlet����API����servlet����ṩ��
public class HelloServlet extends HttpServlet{
	//��д�����еķ�����service()����
	//service  alt+/
	//������������ķ������
	@Override//��д
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������Ĵ����߼��ɳ���Ա��д
		//requestר�Ŵ�������
		//response������Ӧ����������
		//���ܣ�ǰ̨������һ��hello����
		//���ܣ���̨��Ҫ�����������
		//���ñ����ʽ
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>��ӭ����Ͽ��</h3>");
		out.close();

	}

}
