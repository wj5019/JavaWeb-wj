package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CartDao;
import cn.tedu.javaweb.pojo.User;



//���Ʋ㣺controller��
@WebServlet("/user/page/updateCartNumServlet")
public class UpdateCartNumServlet extends HttpServlet{
	//��д������ڵķ���
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����request�ı����ʽ
		request.setCharacterEncoding("utf-8");
		//��Ϊ��ajax��������response��ʽ����Ҫ����html
		//��ȡҳ���е�ajax����
		String num=request.getParameter("num");
		String id=request.getParameter("itemId");
		//��ȡ��ǰ���û���Ϣ
		User user=(User)request.getSession().getAttribute("user");
		//����dao��ķ������������ݿ���Ϣ
		/*1.����rid��Ҳ����cart���ﳵ�����������и��¼���
		 * 2.���µķ���������Ҫ����ֵ
		 * 
		 */
		CartDao dao=new CartDao();
		dao.updateCartNum(Integer.parseInt(id), Integer.parseInt(num));//ת������
		//ʹ��io���������������
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
	}

}
