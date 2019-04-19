package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CartDao;
import cn.tedu.javaweb.pojo.CartAndBook;
import cn.tedu.javaweb.pojo.User;

//controller层
public class AllCartServlet extends HttpServlet{
	//编写服务入口的方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码格式
		request.setCharacterEncoding("utf-8");
		//设置response的编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取页面中的参数，指的是从session（服务端的对话对象）中得到user对象信息
		User user=(User)request.getSession().getAttribute("user");
		//调用dao层的方法，查询全部的cart购物车信息
		/*
		 * 1.根据uid查询该用户下所有的cart对象以及对应的book对象
		 * 2.把获取的结果封装到ArrayList<BookAndCart>集合中
		 * 
		 */
		CartDao dao=new CartDao();
		ArrayList<CartAndBook> list=dao.selectAllByUid(user.getPhone());
		//把list集合封装到request对象中
		request.setAttribute("cabs", list);
		//请求的转发：需要把数据传递到jsp页面中//重定向是不传参的
		request.getRequestDispatcher("cart.jsp").forward(request, response);;
	}

}
