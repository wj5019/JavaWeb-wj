package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
//��ѯ�����鼮serlvrt
public class AllBookServlet extends HttpServlet{
	//��������ķ������
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����dao��ķ�������ɲ�ѯ�鼮�Ĳ���
		BookDao dao=new BookDao();

	}

}
