package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CartDao;
import cn.tedu.javaweb.pojo.User;



//控制层：controller层
@WebServlet("/user/page/updateCartNumServlet")
public class UpdateCartNumServlet extends HttpServlet{
	//重写服务入口的方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码格式
		request.setCharacterEncoding("utf-8");
		//因为是ajax请求，所以response格式不需要配置html
		//获取页面中的ajax请求
		String num=request.getParameter("num");
		String id=request.getParameter("itemId");
		//获取当前的用户信息
		User user=(User)request.getSession().getAttribute("user");
		//调用dao层的方法；更新数据库信息
		/*1.根据rid，也就是cart购物车的主键，进行更新即可
		 * 2.更新的方法，不需要返回值
		 * 
		 */
		CartDao dao=new CartDao();
		dao.updateCartNum(Integer.parseInt(id), Integer.parseInt(num));//转换类型
		//使用io流向浏览器输出结果
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
	}

}
