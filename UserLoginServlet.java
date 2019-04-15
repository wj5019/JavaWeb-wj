package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			//ʹ��session��user�����¼����
			HttpSession session=request.getSession();
			//key:"key"��ʾ����Ա�Զ��������. "value"�����ʾ�û��ĵ�ǰ��½��Ϣ
			session.setAttribute("user", user);
			
			//-------------Cookie��������ɣ������ڿͻ���----------------------------
			String rename = request.getParameter("rename");//��ȡ��ѡ���ס�û���������
			if ("true".equals(rename)) {//�ѹ�ѡ
				//����һ��cookieר�Ŵ��uname�û���
				Cookie cookie = new Cookie("rename",URLEncoder.encode(uname, "utf-8"));//��uname��utf-8ת��
				System.out.println(request.getContextPath());
				cookie.setPath(request.getContextPath()+"/");//�Ӹ�Ŀ¼��ȡ���cookie��·��,
				cookie.setMaxAge(30*24*60*60);//����cookie��ŵ��ʱ�䣬��������д���
				response.addCookie(cookie);
			}else {//δ��ѡ
				//ȡ����ѡʱ����Ҫ��cookie������ղ���,û�취ֱ��ɾ���������٣�ֻ�ܱ��࣬
				//����ͬ·��������cookie����ԭ��cookie�����ʱ��Ϊ0
				Cookie cookie = new Cookie("rename","");//û��Ҫ���н���,�����ǿյ�
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			
			//ʹ��ת��������������ٴη���
			request.getRequestDispatcher("allBookServlet").forward(request, response);
		}else {//���û��û������������
			response.sendRedirect("login.html");//�ض���
		}
		
//		//ʹ��io����������������Ϣ
//		PrintWriter out = response.getWriter();
//		out.println("<h3>��ϲ��"+uname+"����½�ɹ�</h3>");
//		out.println("<6>����������:"+upwd+"</6>");
//		out.close();
	}

}
