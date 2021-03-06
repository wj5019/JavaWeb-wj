package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;
import cn.tedu.javaweb.service.BookService;
import cn.tedu.javaweb.service.BookServiceImpl;
//查询所有书籍serlvrt
public class AllBookServlet extends HttpServlet{
	//处理请求的服务入口
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码格式
		request.setCharacterEncoding("utf-8");
		//设置response的编码格式
		response.setContentType("text/html;charaset=utf-8");
		
		/*//调用dao层的方法，完成查询书籍的操作
		BookDao dao=new BookDao();
		//调用查询全部数据的方法
		ArrayList<Book> list=dao.selectAll();
		//把数据集合封装到request对象中 转发带参 重定向不带参
		request.setAttribute("books", list);//设置属性*/
		
		//获取页面中的请求参数：currentPage/pageSize
		String currentPage = request.getParameter("currentPage");//当前页
		String pageSize = request.getParameter("pageSize");//每一页的数量 
		/*需要调用业务逻辑层专门处理分页业务
		 * 1.定义一个接口：Bookservice（业务的接口）
		 * 2.定义一个实现类：BookserviceImpl（业务的实现类）
		 */
		BookService bs = new BookServiceImpl();
		//发生了调用
		PageBean<Book> pg = bs.selectBookByPage(currentPage, pageSize);
		request.setAttribute("pg", pg);
		request.setAttribute("books", pg.getPageList());
		
		//页面跳转
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
