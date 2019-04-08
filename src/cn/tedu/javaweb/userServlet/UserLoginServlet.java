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
	//��д���������service����
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //����request����ı����ʽ
		request.setCharacterEncoding("UTF-8");
		//����response��Ӧ�ı����ʽ
		response.setContentType("text/html;charset=UTF-8");
		//ͨ��request���󣬻�ȡ��¼���û���������
		//������getParameter("����")������������ҳ�棬������Ӧ����<input name="uname"/>
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		
		//����dao��ķ����ж��Ƿ���ڸõ�¼�û�
		UserDao dao=new UserDao();
		User user=dao.selectByUnameAndUpwd(uname,upwd);
		if (user!=null) {//���û����Ե�¼
//			response.sendRedirect("index.html");
			request.getRequestDispatcher("allBookServlet").forward(request, response);
		}else {//���û��û������������
			response.sendRedirect("login.html");
		}
		
//		//ʹ��io����������������Ϣ
//		PrintWriter out = response.getWriter();
//		out.println("<h3>��ϲ��"+uname+"����½�ɹ�</h3>");
//		out.println("<6>����������:"+upwd+"</6>");
//		out.close();
	}

}
