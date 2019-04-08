package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
//查询所有书籍serlvrt
public class AllBookServlet extends HttpServlet{
	//处理请求的服务入口
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用dao层的方法，完成查询书籍的操作
		BookDao dao=new BookDao();

	}

}
