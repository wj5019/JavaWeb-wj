package cn.tedu.javaweb.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import cn.tedu.javaweb.pojo.User;
import cn.tedu.javaweb.utils.C3P0;
import cn.tedu.javaweb.utils.JDBC;

//ר���������ݿ�ı����(CRUD����ɾ�Ĳ�)
//���ݷ��ʲ�
//��:t_user(������5���ֶ�)
public class UserDao {
    //��ѯ���ݿ�Ĳ���
	public void selectALL(){
		//1.��ȡ�������ݿ�����
		Connection conn=JDBC.getConnection();
		//2.��дSQL���
		String sql="select *from t_user";
		//3.��ȡִ��SQL���Ķ���
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
		    //4.ִ��SQL��䲢��ý����
			ResultSet rs=ps.executeQuery();
			//5.ѭ�����������
			while(rs.next()){//��ȡ��һ����Ϣ
				String uname=rs.getString("uname");
				System.out.println(uname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ////Ϊ������ֵ
		
		
	}
	//�������ݿ�Ĳ���
	public void insert(String phone,String uname,String upwd,String email,int role){
		//��ȡ���ݿ�����
		Connection conn=JDBC.getConnection();
		//2.��дsql���
		String sql="insert into t_user values(?,?,?,?,?)";
		//3.��ȡִ��sql���Ķ���
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			 //Ϊ������ֵ
			ps.setString(1, phone);
			ps.setString(2, uname);
			ps.setString(3, upwd);
			ps.setString(4, email);
			ps.setInt(5, role);
			//4.ִ��sql���
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�޸����ݿ�Ĳ���
	//�����û��ֻ����޸�����
	public void update(String upwd,String phone){
		//1.��ȡ���ݿ������
		Connection conn=JDBC.getConnection();
		//2.��дsql���
		String sql="update t_user set upwd=? where phone=?";
		//3.��ȡִ��sql���Ķ���
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//Ϊ����ֵ
			ps.setString(1, upwd);
			ps.setString(2, phone);
			//4.ִ��sql���
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//ɾ�����ݿ�Ĳ���
	//����phoneɾ��һ����¼
	public void delete(String phone){
		Connection conn=JDBC.getConnection();
		String sql="delete form t_user where phone=? ";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ר�����ڼ����û��Ƿ����
	//������uname��upwd
	//����ֵ���ͣ�user���ͣ����ڼ�¼��ǰ���û���Ϣ
	public User selectByUnameAndUpwd(String uname,String upwd) {
		//����һ��user����
		User user=null;
		//�������ݿ⣬��ѯ���ݿ�
		Connection conn=JDBC.getConnection();
		String sql="select * from t_user where uname=? and upwd=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uname);//�ԣ���ֵ
			ps.setString(2, upwd);
			//ʹ�ý��������sql����ִ�н��
			ResultSet rs=ps.executeQuery();
			//�Խ����������user����ķ�װ
			if(rs.next()) {//�������������ô��װ
				user=new User();//����һ������
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����һ��user����
		return user;
	}
	
	
	//ajaxУ���û���������ֵ������boolean���ͣ��жϸ��û��Ƿ����
	public boolean ajaxCheckUname(String uname) {
		//����һ��boolean�����ж��û���
		boolean flag=false;
		///////////////////JDBC:C3P0/////////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_user where uname=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//��ֵ
			ps.setString(1, uname);
			//�����
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {//�������һ����¼����ô֤�����û��Ѵ���
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����һ��boolean����
		return flag;
	}
	
	public boolean ajaxCheckEmail(String email) {
		//����һ��boolean�����ж�����
		boolean flag=false;
		///////////////////JDBC:C3P0/////////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_user where email=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//��ֵ
			ps.setString(1, email);
			//�����
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {//�������һ����¼����ô֤�����û��Ѵ���
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����һ��boolean����
		return flag;
	}
	
	public boolean ajaxCheckPhone(String phone) {
		//����һ��boolean�����ж��ֻ���
		boolean flag=false;
		///////////////////JDBC:C3P0/////////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_user where phone=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//��ֵ
			ps.setString(1, phone);
			//�����
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {//�������һ����¼����ô֤�����û��Ѵ���
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����һ��boolean����
		return flag;
	}
	public static void main(String[] args){
		UserDao dao=new UserDao();
//	    dao.selectALL();
//	    dao.insert("ssss", "����", "123", "659qq@.com", 0);
//	    dao.update("00000000000", "12345678900");
//	    dao.delete("12345678901");
//		User user=dao.selectByUnameAndUpwd("�黨��111", "12345678");
//		System.out.println(user);
		boolean flag=dao.ajaxCheckUname("qqqqq");
		System.out.println(flag);
				
	}
}



	