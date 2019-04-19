package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CartDao;
import cn.tedu.javaweb.pojo.Cart;
import cn.tedu.javaweb.pojo.User;

//controller控制层
public class AddCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码格式
		request.setCharacterEncoding("utf-8");
		//获取页面中请求url的参数
		String newcount=request.getParameter("count");
		String isbn=request.getParameter("product");
		User user=(User)request.getSession().getAttribute("user");
		String uid=user.getPhone();
		//需要调用dao层的方法
		/*1.判断数据库中，是否已经存在该本书的购物车信息
		 * 2.如果原来已添加过购物车，那么对购物车信息跟新
		 * 3.如果原来没有添加过购物车信息，那么对购物车信息插入
		 */
		CartDao dao=new CartDao();
		Cart cart=dao.selectByUidAndIsbn(uid,isbn);
		if (cart!=null) {//表示原来已经添加过购物车
			int oldcount=cart.getCount();
			int totalcount=oldcount+Integer.parseInt(newcount);
			dao.update(cart.getRid(),totalcount);
		}else {//表示原来没有添加过购物车
			dao.insert(uid, isbn,Integer.parseInt(newcount));
		}
		//使用io流返回给浏览器一个字符串结果
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
	}

}
