package cn.tedu.javaweb.service;

import java.util.ArrayList;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

//业务逻辑层的具体实现类
//由程序员手动编写，处理具体的业务
public class BookServiceImpl implements BookService{//接口，下面几行不用手动写

	@Override
	public PageBean<Book> selectBookByPage(String currentPage, String pageSize) {
		// 定义一个分页对象
		PageBean<Book> pageBean = new PageBean<Book>();
		//pageBean对象：封装当前的页数  //如果等于null默认第一页，不等于null类型转换
		if (currentPage == null) {
			currentPage = "1";//默认首页
		}
		int i_currentPage = Integer.parseInt(currentPage);
		pageBean.setCurrentPage(Integer.parseInt(currentPage));
		//pageBean对象：封装每一页的数量
		if (pageSize == null) {
			pageSize = "8";//默认每一页8本书
		}
		int i_pageSize = Integer.parseInt(pageSize);
		//pageBean对象：封装书籍的总数量
		/*1.需要调用dao层的方法：selectAll(),获取全部书籍
		 * 2.根据list集合，获取list.size()即是总数量
		 * 
		 */
		BookDao dao = new BookDao();
		ArrayList<Book> books = dao.selectAll();
		int i_totalCount = books.size();
		pageBean.setTotalCount(i_totalCount);
		//pageBean对象：封装总页数
		//公式：总页数=总数量/每一页的数量+1
		//举例：总页数=100/8=12+1
		//向上取整：Math.ceil()
		//向下取整：Math.floor()
		int i_totalPages = (int) (Math.floor(i_totalCount/i_pageSize)+1);//向上取整
		pageBean.setTotalPages(i_totalPages);
		//pageBean对象：封装分页的数据
		//需要调用dao层的方法
		//举例：第一页：0开始数，8个记录 -->从0数到7
		//      第二页：8开始数，8个记录 -->从8数到15
		//      第三页：16开始数，8个记录-->从16数到23
		//公式：startRow = (currentPage-1)*pageSize;
		int startRow = (i_currentPage - 1)*i_pageSize;
		ArrayList<Book> list = dao.selectBookByPages(startRow, i_pageSize);
		pageBean.setPageList(list);//封装
		return pageBean;
	}

}
