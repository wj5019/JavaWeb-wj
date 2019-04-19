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
	//编写处理请求的service方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request请求的编码格式
		request.setCharacterEncoding("UTF-8");
		//设置response响应的编码格式
		response.setContentType("text/html;charset=UTF-8");
		//通过request对象，获取登录的用户名和密码
		//参数：getParameter("参数")，参数来自于页面，参数对应的是<input name="uname"/>
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		
		//获取页面中验证码文本框的输入内容
		String valistr = request.getParameter("valistr");
		//获取session中存放的验证码（图片上的验证码）
		String code = (String)request.getSession().getAttribute("code");
		//当输入的验证码与图片不匹配时不能登陆，不正确时返回一个错误信息
		if (!code.equalsIgnoreCase(valistr)) {//忽略大小写
			request.setAttribute("showResult", "验证码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		
		//调用dao层的方法判断是否存在该登录用户
		UserDao dao=new UserDao();
		User user=dao.selectByUnameAndUpwd(uname,upwd);
		if (user!=null) {//该用户可以登录
//			response.sendRedirect("index.html");
			//使用session把user对象记录下来
			HttpSession session=request.getSession();
			//key:"key"表示程序员自定义的名字. "value"对象表示用户的当前登陆信息
			session.setAttribute("user", user);
			
			//-------------Cookie服务端生成，保存在客户端----------------------------
			String remname = request.getParameter("remname");//获取复选框记住用户名的内容
			if ("true".equals(remname)) {//已勾选
				//生成一个cookie专门存放uname用户名
				Cookie cookie = new Cookie("remname",URLEncoder.encode(uname, "utf-8"));//对uname用utf-8转码
				System.out.println(request.getContextPath());
				cookie.setPath(request.getContextPath()+"/");//从根目录获取存放cookie的路径,
				cookie.setMaxAge(30*24*60*60);//设置cookie存放的最长时间，按照秒进行存入
				response.addCookie(cookie);
			}else {//未勾选
				//取消勾选时，需要把cookie进行清空操作,没办法直接删除或者销毁，只能变相，
				//在相同路径下用新cookie代替原来cookie设置最长时间为0
				Cookie cookie = new Cookie("remname","");//没必要进行解码,里面是空的
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			//---------------------Cookie自动登陆--------------------------------------------------
			//获取页面中自动登陆的复选框内容
			String autologin = request.getParameter("autologin");
			if ("true".equals(autologin)) {//已勾选自动登陆
				//自动登录需要用户名以及密码
				Cookie cookie = new Cookie("autologin",URLEncoder.encode(uname, "utf-8")+"#"+upwd);//字符串的拼接
			    cookie.setPath(request.getContextPath()+"/");
			    cookie.setMaxAge(30*24*60*60);
			    response.addCookie(cookie);
			}else {//未勾选
				
			}
			
			
			//使用转发，进行请求的再次发送
			request.getRequestDispatcher("allBookServlet").forward(request, response);
		}else {//该用户用户名或密码错误
			response.sendRedirect("login.html");//重定向
		}
		
//		//使用io流输出到浏览器的信息
//		PrintWriter out = response.getWriter();
//		out.println("<h3>恭喜你"+uname+"，登陆成功</h3>");
//		out.println("<6>您的密码是:"+upwd+"</6>");
//		out.close();
	}

}
