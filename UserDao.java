package cn.tedu.javaweb.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import cn.tedu.javaweb.utils.JDBC;

//专门用于数据库的表操作(CRUD、增删改查)
//数据访问层
//表:t_user(其中有5个字段)
public class UserDao {
    //查询数据库的操作
	public void selectALL(){
		//1.获取并打开数据库连接
		Connection conn=JDBC.getConnection();
		//2.编写SQL语句
		String sql="select *from t_user";
		//3.获取执行SQL语句的对象
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
		    //4.执行SQL语句并获得结果集
			ResultSet rs=ps.executeQuery();
			//5.循环遍历结果集
			while(rs.next()){//获取下一条信息
				String uname=rs.getString("uname");
				System.out.println(uname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ////为语句对象赋值
		
		
	}
	//新增数据库的操作
	public void insert(String phone,String uname,String upwd,String email,int role){
		//获取数据库连接
		Connection conn=JDBC.getConnection();
		//2.编写sql语句
		String sql="insert into t_user values(?,?,?,?,?)";
		//3.获取执行sql语句的对象
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			 //为语句对象赋值
			ps.setString(1, phone);
			ps.setString(2, uname);
			ps.setString(3, upwd);
			ps.setString(4, email);
			ps.setInt(5, role);
			//4.执行sql语句
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//修改数据库的操作
	//根据用户手机号修改密码
	public void update(String upwd,String phone){
		//1.获取数据库的连接
		Connection conn=JDBC.getConnection();
		//2.编写sql语句
		String sql="update t_user set upwd=? where phone=?";
		//3.获取执行sql语句的对象
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//为对象赋值
			ps.setString(1, upwd);
			ps.setString(2, phone);
			//4.执行sql语句
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//删除数据库的操作
	//根据phone删除一条记录
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
	public static void main(String[] args){
		UserDao dao=new UserDao();
	    dao.selectALL();
//	    dao.insert("ssss", "马云", "123", "659qq@.com", 0);
//	    dao.update("00000000000", "12345678900");
//	    dao.delete("12345678901");
	}
}
	
