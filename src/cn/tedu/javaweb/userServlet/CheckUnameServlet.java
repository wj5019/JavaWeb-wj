package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;

//专门用于请求的处理和响应
public class CheckUnameServlet extends HttpServlet{
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //设置request编码格式
	  request.setCharacterEncoding("utf-8");
	  //设置response编码格式
	  //当使用ajax时，需要的结果是一个字符串
	  //如果设置了html格式，那么有的浏览器就会认为yes为一个html标签
	  response.setContentType("text/html;charset=utf-8");
	  //获取页面中的参数：uname
	  //此处的uname字段指的是ajax中的data：'uname'+值。regist171行
	  String uname=request.getParameter("uname");
	  System.out.println(uname);
	  //调用dao层的方法：ajaxCheckUname
	  UserDao dao=new UserDao();
	  //如果该用户已存在时：使用io流向浏览器输出一个字符串
	  PrintWriter writer=response.getWriter();
	  if (dao.ajaxCheckUname(uname)) {
		  writer.write("yes");//该用户被占用
	}else {
		 writer.close();//该用户没有被占用
	}
	  
}
}
