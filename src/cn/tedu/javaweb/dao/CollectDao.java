package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.tedu.javaweb.pojo.Collect;
import cn.tedu.javaweb.utils.C3P0;

//Dao�㣺���ݷ��ʲ�
//ר�Ž������ݿ����ɾ�Ĳ�Ĳ���
public class CollectDao {
	public static void main(String[] args) {
		CollectDao dao = new CollectDao();
		String uid="12345678901";
		String isbn="9787115130228";
		Collect col = dao.selectByUidAndIsbn(uid, isbn);
		System.out.println(col);
		dao.delete(uid, isbn);
		System.out.println("ɾ���ɹ�");
	}
	/* 
	 * 1.1.��ѯ���ݿ����Ƿ��Ѿ��ղ�
	 */
	 public Collect selectByUidAndIsbn(String uid,String isbn) {
		 ////����һ��collect����
		 Collect col = null;
		 ////////////////JDBC:C3P0///////////////////////
		 Connection conn = C3P0.getConnection();
		 String sql = " select * from t_collect where uid=? and book=? ";
		 try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//��������Ȿ����ղ�
				col = new Collect();
				col.setBook(rs.getString("book"));
				col.setUid(rs.getString("uid"));
				col.setRid(rs.getInt("rid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0.closeConnection(conn);
		}
		 //����һ��collect����
		 return col;
	 }
	/*
	 * 2.������ղأ�������ղذ�ť��ʱ������ȡ���ղ�
	 */
	public void delete(String uid,String isbn) {
		//////////////JDBC:C3P0/////////////////
		Connection conn = C3P0.getConnection();
		String sql = "delete from t_collect where uid=? and book=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0.closeConnection(conn);
		}
	}
	/*
	 * 3.���δ�ղأ�������ղذ�ť��ʱ����������ղ�
	 */
	public void insert(String uid , String isbn) {
		//////////////JDBC:C3P0/////////////////
		Connection conn = C3P0.getConnection();
		String sql = "insert into t_collect values(default,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0.closeConnection(conn);
		}
	}
	
	
	
}
