package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/page/deleteCartServlet")
public class DelectCartSerlvet extends HttpServlet{
	//�������
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ��url�Ĳ���
		
		//����dao�㣺����rid��ɾ��cart�������
		
		//ҳ�����ת����ɾ�����ݺ���Ҫ����ˢ��ҳ��
		//��ν��ˢ��ҳ�棬��������һ��allCartSerlcet����
		request.getRequestDispatcher("allCartServlet").forward(request, response);
	}
	

}
