package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.dao.CollectDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.Collect;
import cn.tedu.javaweb.pojo.User;

//商品详情的页面
public class DetailBookServlet extends HttpServlet{
	//提供服务入口
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request编码格式
		request.setCharacterEncoding("utf-8");
		//设置response编码格式
		response.setContentType("text/html;charset=utf-8");
		//调用dao层的方法，查询一本书的详细信息
		BookDao dao=new BookDao();
		//先获取一本书的isbn编号
		String isbn=request.getParameter("isbn");
		//使用dao.selectByIsbn(isbn)方法
		Book b=dao.selectByIsbn(isbn);
		//把book对象封装到request对象中
		request.setAttribute("book", b);
		//添加推荐的书籍信息
		ArrayList<Book> list=dao.selectAll();
		ArrayList<Book> newList=new ArrayList<Book>();
		//使用随机数，随机出四本书
		for (int i = 0; i < 4; i++) {
			int random=(int)(Math.random()*list.size());
			Book bo=list.get(random);
			newList.add(bo);
			list.remove(random);
		}
		request.setAttribute("newList", newList);
		
		//判断收藏夹是小黄还是小白
		CollectDao cdao=new CollectDao();
        User user=(User)request.getSession().getAttribute("user");
		Collect col=cdao.selectByUidAndIsbn(user.getPhone(), isbn);
		if (col!=null) {//表示已收藏,黄..62.png
			request.setAttribute("isCollect", "2");
		}else {//表示未收藏。白..6.png
			request.setAttribute("isCollect", "");
		}
		
		//请求的转发：把request对象以及response对象转发到detail.jsp页面
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}

	

}
