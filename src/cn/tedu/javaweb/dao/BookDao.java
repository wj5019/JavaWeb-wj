package cn.tedu.javaweb.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.utils.C3P0;

//数据访问层：增删改查
//需要依赖的类是数据模型
public class BookDao {
	//自定义方法：selectAll()
	//参数类型是list集合，里面存放了所有的书籍信息
	public ArrayList<Book> selectAll(){
		ArrayList<Book> list =new ArrayList<Book>();
		//使用C3P0打开数据库连接
		Connection conn=C3P0.getConnection();
		//编写sql语句
		String sql="select * from t_book";
		try {
			//编写执行sql语句的对象
			PreparedStatement ps=conn.prepareStatement(sql);
		    //执行查询操作
			ResultSet res=ps.executeQuery();
			//循环遍历结果集
			while (res.next()) {
				Book book=new Book();
				book.setAuthor(res.getString("author"));
				book.setEdition(res.getInt("edition"));
				book.setForm(res.getString("form"));
				book.setFormat(res.getString("format"));
				book.setIsbn(res.getString("isbn"));
				book.setPackaging(res.getString("packaging"));
				book.setPages(res.getInt("pages"));
				book.setPress(res.getString("press"));
				book.setPrice(res.getDouble("price"));
				book.setPublished(res.getDate("published"));
				book.setTitle(res.getString("title"));
				book.setWords(res.getInt("words"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		//返回一个list对象的值
		return list;
	}
	
	//根据isbn查询商品的详情页.参数是isbn，返回值是book对象
	public Book selectByIsbn(String isbn) {
		//定义一个book对象
		Book book=null;
		////////////////////JDBC//////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_book where isbn=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet res=ps.executeQuery();
			//判断res结果集中是否存在信息
			if (res.next()) {
				book=new Book();
				//为12个字段赋值
				book.setAuthor(res.getString("author"));
				book.setEdition(res.getInt("edition"));
				book.setForm(res.getString("form"));
				book.setFormat(res.getString("format"));
				book.setIsbn(res.getString("isbn"));
				book.setPackaging(res.getString("packaging"));
				book.setPages(res.getInt("pages"));
				book.setPress(res.getString("press"));
				book.setPrice(res.getDouble("price"));
				book.setPublished(res.getDate("published"));
				book.setTitle(res.getString("title"));
				book.setWords(res.getInt("words"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
		//返回回一个book对象
		return book;
	}
	
	//参数：int startRow：指的是数据库中的下标/索引，从0开始数数
	public ArrayList<Book> selectBookByPages(int startRow,int pageSize) {
		ArrayList<Book> list = new ArrayList<Book>();
		Book book = null;
		Connection conn = C3P0.getConnection();
		String sql = "select * from t_book limit ?,?";
		//从第一行（从0开始）记录开始向下数八条记录
		//数据库从0开始数数
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, pageSize);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				book = new Book();
				//为12个字段赋值
				book.setAuthor(res.getString("author"));
				book.setEdition(res.getInt("edition"));
				book.setForm(res.getString("form"));
				book.setFormat(res.getString("format"));
				book.setIsbn(res.getString("isbn"));
				book.setPackaging(res.getString("packaging"));
				book.setPages(res.getInt("pages"));
				book.setPress(res.getString("press"));
				book.setPrice(res.getDouble("price"));
				book.setPublished(res.getDate("published"));
				book.setTitle(res.getString("title"));
				book.setWords(res.getInt("words"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0.closeConnection(conn);
		}
		
		return list;
	}
	
	
	//insert插入数据库
	public void insert(Book book) {
		Connection con = C3P0.getConnection();
		String sql = "insert into "
				+ "t_book(isbn,title,author,price,press,edition,published,pages,words,packaging,format,form) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement sta = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, book.getIsbn());
			sta.setString(2, book.getTitle());
			sta.setString(3, book.getAuthor());
			sta.setDouble(4, book.getPrice());
			sta.setString(5, book.getPress());
			sta.setInt(6, book.getEdition());
			sta.setDate(7, new java.sql.Date(book.getPublished().getTime()));
			sta.setInt(8, book.getPages());
			sta.setInt(9, book.getWords());
			sta.setString(10, book.getPackaging());
			sta.setString(11, book.getFormat());
			sta.setString(12, book.getForm());
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			C3P0.closeConnection(con);
		}
	}

	
	 

	
	public static void main(String[] args) {
		BookDao dao=new BookDao();
//		ArrayList<Book> list=dao.selectAll();
//		for (Book b : list) {
//			System.out.println(b.getTitle());
//		}
		String isbn="9787111213826";
		Book b=dao.selectByIsbn(isbn);
		System.out.println(b);
	}

}
