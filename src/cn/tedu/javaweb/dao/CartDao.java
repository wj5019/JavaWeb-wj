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

//Dao�����ݷ��ʲ�
public class CartDao {
	//1.����uid��isbn��ѯ���ݿ�
	public Cart selectByUidAndIsbn(String uid,String isbn) {
		//����һ��cart����
		Cart cart=null;
		Connection conn=C3P0.getConnection();
		String sql="select * from t_cart where uid=? and book=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){//��ʾ�鵽��һ�����еĹ��ﳵ��Ϣ
				//�����ݿ��װ��cart������
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
		
		//����һ��cart����
		return cart;
		
	}
	//2.����uid��isbn��rid��totalcount�������ݿ�
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
	//3.����uid��isbn��count�������ݿ�
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
	
	//��ѯȫ�����ﳵ
	public ArrayList<CartAndBook> selectAllByUid(String uid){
		//����һ�����϶���
		ArrayList<CartAndBook> list=new ArrayList<CartAndBook>();
		//����һ��cart����
		Cart cart=null;
		//����һ��book����
		Book book=null;
		//����һ��cartandbook����
		CartAndBook cab=null;
		//////////////////////JDBC:C3P0////////////////////
		Connection conn=C3P0.getConnection();
		String sql="SELECT tc.*,tb.* FROM t_book tb INNER JOIN t_cart tc ON tb.isbn=tc.book WHERE tc.uid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet res=ps.executeQuery();
			while (res.next()) {
				//1.����cart���󣬲����и�ֵ
				cart=new Cart();
				cart.setRid(res.getInt("rid"));
				cart.setUid(res.getString("uid"));
				cart.setBook(res.getString("book"));
				cart.setCount(res.getInt("count"));
				//2.����book���󣬲����и�ֵ
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
				//3.����CartAndBook���󣬲����и�ֵ
				cab=new CartAndBook();
				cab.setCart(cart);
				cab.setBook(book);
				//4.��cab���󣬴��뵽list������
				list.add(cab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		//����һ�����϶���
		return list;		
	}
	
	//���Ӽ��Ÿ������ݿ⡿
	public void updateCartNum(int rid,int num) {
		Connection conn=C3P0.getConnection();
		String sql="update t_cart set count=? where rid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,num);
			ps.setInt(2,rid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	
	public static void main(String[] args) {
		CartDao dao=new CartDao();
//		dao.insert("110","����",20);
//		System.out.println("������������");
//		dao.update(2, 222222);
//		System.out.println("��������");
//		Cart cart=dao.selectByUidAndIsbn("110", "����");
//		System.out.println(cart);
		ArrayList<CartAndBook> list=dao.selectAllByUid("13056239502");
		for (CartAndBook cab:list) {
			System.out.println(cab);
		}

	}

}
