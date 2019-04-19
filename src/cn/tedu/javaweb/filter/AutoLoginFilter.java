package cn.tedu.javaweb.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.Filter.Chain;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.pojo.User;

//�Զ���½�Ĺ�����
public  class AutoLoginFilter implements Filter{

	//���ٷ�������Ҫ�˹������ɷ������Զ����ý�������
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//���ĵĲ��֣��ɳ���Ա��д���������߼�
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// 1.���ز���ȡrequest����.һ����ȡresponse����
		HttpServletRequest request = (HttpServletRequest)arg0;//ǿ������ת��
		HttpServletResponse response = (HttpServletResponse)arg1;
		/*ǰ������������Ҫ��cookie�ſ��Խ����Զ����еĲ�������cookie����˵�¼���û���������
		 * 2.1�鿴session�Ự���Ƿ���user����  (�����û�ر���session���ر�����cookie)
		 * 2.2���û��session������ôͨ��cookie����user����Ļ�ȡ
		 */
		if (request.getSession(false)==null || request.getSession().getAttribute("user")!=null) {
			//�ж���sessionû��ʱ����Ҫcookie�Զ���½
			//��ȡ�Զ���½��cookies����
			Cookie[] cookies = request.getCookies();
			Cookie autologinCookie = null;
			if (cookies!=null) {
				for (Cookie cookie : cookies) {
					//��ȡautologin��cookie
					if ("autologin".equals(cookie.getName())) {//autologin�Զ���½�ַ���
						autologinCookie = cookie;//�Զ���¼��cookie
						break;
					}
				}
			}
			//�жϣ�����ȡ��autologin��cookie��Ϊ��ʱ�����ܴ�cookie���õ��û�������
			if(autologinCookie!=null) {
				String uname_upwd = autologinCookie.getValue();
				//�ַ�����ƴ����ʽ��������#333
				String uname = URLDecoder.decode(uname_upwd, "utf-8").split("#")[0];//����,���,���������λ
				String upwd = URLDecoder.decode(uname_upwd, "utf-8").split("#")[1];
			    //�����û����������ѯ���ݿ����Ƿ���ڴ���
				UserDao dao = new UserDao();
				User user = dao.selectByUnameAndUpwd(uname, upwd);
				if (user != null) {//��user����ʱ������session��
					request.getSession().setAttribute("user", user);					
				}
			}
			
		}
		//4.�����Ƿ��Զ���½���������е�����
		//chain->FilterChain������������
		//�ù������Ǹ÷���doFilter��һ������
		chain.doFilter(request, response);
	}

	//��ʼ����������filter���󱻴�������ʱ��serlvet��������ø÷���Ĭ�ϳ�ʼ��
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
