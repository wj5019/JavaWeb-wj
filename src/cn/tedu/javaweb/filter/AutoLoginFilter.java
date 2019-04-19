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

//自动登陆的过滤器
public  class AutoLoginFilter implements Filter{

	//销毁方法不需要人工处理，由服务器自动调用进行销毁
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//核心的部分，由程序员编写过滤器的逻辑
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// 1.拦截并获取request对象.一并获取response对象
		HttpServletRequest request = (HttpServletRequest)arg0;//强制类型转换
		HttpServletResponse response = (HttpServletResponse)arg1;
		/*前置条件：必须要有cookie才可以进行自动放行的操作，该cookie存放了登录的用户名和密码
		 * 2.1查看session会话中是否有user对象  (浏览器没关闭用session，关闭了用cookie)
		 * 2.2如果没有session对象，那么通过cookie进行user对象的获取
		 */
		if (request.getSession(false)==null || request.getSession().getAttribute("user")!=null) {
			//判断在session没有时，需要cookie自动登陆
			//获取自动登陆的cookies数组
			Cookie[] cookies = request.getCookies();
			Cookie autologinCookie = null;
			if (cookies!=null) {
				for (Cookie cookie : cookies) {
					//获取autologin的cookie
					if ("autologin".equals(cookie.getName())) {//autologin自动登陆字符串
						autologinCookie = cookie;//自动登录的cookie
						break;
					}
				}
			}
			//判断：当获取的autologin的cookie不为空时，才能从cookie中拿到用户名密码
			if(autologinCookie!=null) {
				String uname_upwd = autologinCookie.getValue();
				//字符串的拼接形式：张三丰#333
				String uname = URLDecoder.decode(uname_upwd, "utf-8").split("#")[0];//解码,拆分,张三丰零号位
				String upwd = URLDecoder.decode(uname_upwd, "utf-8").split("#")[1];
			    //根据用户名和密码查询数据库中是否存在此人
				UserDao dao = new UserDao();
				User user = dao.selectByUnameAndUpwd(uname, upwd);
				if (user != null) {//当user存在时，存入session中
					request.getSession().setAttribute("user", user);					
				}
			}
			
		}
		//4.无论是否自动登陆都放行所有的请求
		//chain->FilterChain过滤链！！！
		//该过滤链是该方法doFilter的一个参数
		chain.doFilter(request, response);
	}

	//初始化方法，在filter对象被创建出来时，serlvet容器会调用该方法默认初始化
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
