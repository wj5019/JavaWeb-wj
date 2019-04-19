package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CartDao;
import cn.tedu.javaweb.pojo.CartAndBook;
import cn.tedu.javaweb.pojo.User;

//controller��
public class AllCartServlet extends HttpServlet{
	//��д������ڵķ���
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����request�ı����ʽ
		request.setCharacterEncoding("utf-8");
		//����response�ı����ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ���еĲ�����ָ���Ǵ�session������˵ĶԻ������еõ�user������Ϣ
		User user=(User)request.getSession().getAttribute("user");
		//����dao��ķ�������ѯȫ����cart���ﳵ��Ϣ
		/*
		 * 1.����uid��ѯ���û������е�cart�����Լ���Ӧ��book����
		 * 2.�ѻ�ȡ�Ľ����װ��ArrayList<BookAndCart>������
		 * 
		 */
		CartDao dao=new CartDao();
		ArrayList<CartAndBook> list=dao.selectAllByUid(user.getPhone());
		//��list���Ϸ�װ��request������
		request.setAttribute("cabs", list);
		//�����ת������Ҫ�����ݴ��ݵ�jspҳ����//�ض����ǲ����ε�
		request.getRequestDispatcher("cart.jsp").forward(request, response);;
	}

}
