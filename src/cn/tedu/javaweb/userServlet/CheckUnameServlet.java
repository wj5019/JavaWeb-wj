package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;

//ר����������Ĵ������Ӧ
public class CheckUnameServlet extends HttpServlet{
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //����request�����ʽ
	  request.setCharacterEncoding("utf-8");
	  //����response�����ʽ
	  //��ʹ��ajaxʱ����Ҫ�Ľ����һ���ַ���
	  //���������html��ʽ����ô�е�������ͻ���ΪyesΪһ��html��ǩ
	  response.setContentType("text/html;charset=utf-8");
	  //��ȡҳ���еĲ�����uname
	  //�˴���uname�ֶ�ָ����ajax�е�data��'uname'+ֵ��regist171��
	  String uname=request.getParameter("uname");
	  System.out.println(uname);
	  //����dao��ķ�����ajaxCheckUname
	  UserDao dao=new UserDao();
	  //������û��Ѵ���ʱ��ʹ��io������������һ���ַ���
	  PrintWriter writer=response.getWriter();
	  if (dao.ajaxCheckUname(uname)) {
		  writer.write("yes");//���û���ռ��
	}else {
		 writer.close();//���û�û�б�ռ��
	}
	  
}
}
