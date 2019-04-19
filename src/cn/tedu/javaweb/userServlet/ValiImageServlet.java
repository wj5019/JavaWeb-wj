package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.utils.VerifyCode;


//ר������һ����֤��ͼƬ���ظ������
@WebServlet("/user/page/valiImageServlet")
public class ValiImageServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����response����Ļ������
		//�����������Ҫ����ͼƬ
		response.setDateHeader("Expires", -1);//��Ӧͷ�Ĺ���ʱ�䣬���Ϲ���
		//Cache-Control:�������
		//no-cache��û�л���
		response.setHeader("Cache-Control", "no-cache");//����һ������
		//������֤��Ķ���ķ���
		VerifyCode vc = new VerifyCode();
		//��ͼƬ���浽response��Ӧ�Ļ�����
		vc.drawImage(response.getOutputStream());
		//��ȡ��֤���ϵ���ĸ������
		String code = vc.getCode();
		//��code���浽session������
		request.getSession().setAttribute("code",code);
		
	}
}
