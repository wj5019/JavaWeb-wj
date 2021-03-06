package cn.tedu.javaweb.service;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

//接口：只是定义业务需要的方法！！！没有具体实现
public interface BookService {
/*
 *  根据具体业务去定义
 *  一般来说就是增删改查的方法
 *  这些方法与dao层的方法一一对应
 * 
 */
	//专门用于分页查询的方法
	//参数：currentPage/pageSize
	//返回值类型：需要依赖【分页的描述类】去获取值
	public PageBean<Book> selectBookByPage(String currentPage,String pageSize);
}
