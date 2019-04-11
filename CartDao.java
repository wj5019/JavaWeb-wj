package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.model.core.ID;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.Cart;
import cn.tedu.javaweb.pojo.CartAndBook;
import cn.tedu.javaweb.utils.C3P0;

//Dao是数据访问层
public class CartDao {
	//1.根据uid，isbn查询数据库
	public Cart selectByUidAndIsbn(String uid,String isbn) {
		//定义一个cart对象
		Cart cart=null;
		Connection conn=C3P0.getConnection();
		String sql="select * from t_cart where uid=? and book=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){//表示查到了一条已有的购物车信息
				//把数据库封装到cart对象中
				cart =new Cart();
				cart.setRid(rs.getInt("rid"));
				cart.setUid(rs.getString("uid"));
				cart.setBook(rs.getString("book"));
				cart.setCount(rs.getInt("count"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally {
			C3P0.closeConnection(conn);
		}
		
		//返回一个cart对象
		return cart;
		
	}
	//2.根据uid，isbn，rid，totalcount更新数据库
	public void update(int rid,int totalcount) {
		Connection conn=C3P0.getConnection();
		String sql="update t_cart set count=? where rid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,totalcount);
			ps.setInt(2,rid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	//3.根据uid，isbn，count插入数据库
	public void insert(String uid,String isbn,int count) {
		Connection conn=C3P0.getConnection();
		String sql="insert into t_cart values(default,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,uid);
			ps.setString(2,isbn);
			ps.setInt(3, count);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	
	//查询全部购物车
	public ArrayList<CartAndBook> selectAllByUid(String uid){
		//定义一个集合对象
		ArrayList<CartAndBook> list=new ArrayList<CartAndBook>();
		//定义一个cart对象
		Cart cart=null;
		//定义一个book对象
		Book book=null;
		//定义一个cartandbook对象
		CartAndBook cab=null;
		//////////////////////JDBC:C3P0////////////////////
		Connection conn=C3P0.getConnection();
		String sql="SELECT tc.*,tb.* FROM t_book tb INNER JOIN t_cart tc ON tb.isbn=tc.book WHERE tc.uid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet res=ps.executeQuery();
			while (res.next()) {
				//1.创建cart对象，并进行赋值
				cart=new Cart();
				cart.setRid(res.getInt("rid"));
				cart.setUid(res.getString("uid"));
				cart.setBook(res.getString("book"));
				cart.setCount(res.getInt("count"));
				//2.创建book对象，并进行赋值
				book=new Book();
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
				//3.创建CartAndBook对象，并进行赋值
				cab=new CartAndBook();
				cab.setCart(cart);
				cab.setBook(book);
				//4.把cab对象，存入到list集合中
				list.add(cab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		//返回一个集合对象
		return list;		
	}
	
	
	public static void main(String[] args) {
		CartDao dao=new CartDao();
//		dao.insert("110","姜茶",20);
//		System.out.println("插入数据正常");
//		dao.update(2, 222222);
//		System.out.println("更新正常");
//		Cart cart=dao.selectByUidAndIsbn("110", "姜茶");
//		System.out.println(cart);
		ArrayList<CartAndBook> list=dao.selectAllByUid("13056239502");
		for (CartAndBook cab:list) {
			System.out.println(cab);
		}

	}

}
