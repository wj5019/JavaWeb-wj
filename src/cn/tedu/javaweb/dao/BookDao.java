package cn.tedu.javaweb.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.utils.C3P0;

//���ݷ��ʲ㣺��ɾ�Ĳ�
//��Ҫ��������������ģ��
public class BookDao {
	//�Զ��巽����selectAll()
	//����������list���ϣ������������е��鼮��Ϣ
	public ArrayList<Book> selectAll(){
		ArrayList<Book> list =new ArrayList<Book>();
		//ʹ��C3P0�����ݿ�����
		Connection conn=C3P0.getConnection();
		//��дsql���
		String sql="select * from t_book";
		try {
			//��дִ��sql���Ķ���
			PreparedStatement ps=conn.prepareStatement(sql);
		    //ִ�в�ѯ����
			ResultSet res=ps.executeQuery();
			//ѭ�����������
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
		//����һ��list�����ֵ
		return list;
	}
	
	//����isbn��ѯ��Ʒ������ҳ.������isbn������ֵ��book����
	public Book selectByIsbn(String isbn) {
		//����һ��book����
		Book book=null;
		////////////////////JDBC//////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_book where isbn=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet res=ps.executeQuery();
			//�ж�res��������Ƿ������Ϣ
			if (res.next()) {
				book=new Book();
				//Ϊ12���ֶθ�ֵ
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
		
		//���ػ�һ��book����
		return book;
	}
	
	//������int startRow��ָ�������ݿ��е��±�/��������0��ʼ����
	public ArrayList<Book> selectBookByPages(int startRow,int pageSize) {
		ArrayList<Book> list = new ArrayList<Book>();
		Book book = null;
		Connection conn = C3P0.getConnection();
		String sql = "select * from t_book limit ?,?";
		//�ӵ�һ�У���0��ʼ����¼��ʼ������������¼
		//���ݿ��0��ʼ����
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, pageSize);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				book = new Book();
				//Ϊ12���ֶθ�ֵ
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
	
	
	//insert�������ݿ�
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
