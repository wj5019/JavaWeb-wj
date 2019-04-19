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
	
	//专门用于检验用户是否存在
	//参数：uname和upwd
	//返回值类型：user类型，用于记录当前的用户信息
	public User selectByUnameAndUpwd(String uname,String upwd) {
		//定义一个user对象
		User user=null;
		//访问数据库，查询数据库
		Connection conn=JDBC.getConnection();
		String sql="select * from t_user where uname=? and upwd=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uname);//对？赋值
			ps.setString(2, upwd);
			//使用结果集接收sql语句的执行结果
			ResultSet rs=ps.executeQuery();
			//对结果集，进行user对象的封装
			if(rs.next()) {//如果存在数据那么封装
				user=new User();//创建一个对象
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
		//返回一个user对象
		return user;
	}
	
	
	//ajax校验用户名，返回值类型是boolean类型，判断该用户是否存在
	public boolean ajaxCheckUname(String uname) {
		//定义一个boolean类型判断用户名
		boolean flag=false;
		///////////////////JDBC:C3P0/////////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_user where uname=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//赋值
			ps.setString(1, uname);
			//结果集
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {//如果存在一条记录，那么证明该用户已存在
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回一个boolean类型
		return flag;
	}
	
	public boolean ajaxCheckEmail(String email) {
		//定义一个boolean类型判断邮箱
		boolean flag=false;
		///////////////////JDBC:C3P0/////////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_user where email=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//赋值
			ps.setString(1, email);
			//结果集
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {//如果存在一条记录，那么证明该用户已存在
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回一个boolean类型
		return flag;
	}
	
	public boolean ajaxCheckPhone(String phone) {
		//定义一个boolean类型判断手机号
		boolean flag=false;
		///////////////////JDBC:C3P0/////////////////////////////
		Connection conn=C3P0.getConnection();
		String sql="select * from t_user where phone=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			//赋值
			ps.setString(1, phone);
			//结果集
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {//如果存在一条记录，那么证明该用户已存在
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回一个boolean类型
		return flag;
	}
	public static void main(String[] args){
		UserDao dao=new UserDao();
//	    dao.selectALL();
//	    dao.insert("ssss", "马云", "123", "659qq@.com", 0);
//	    dao.update("00000000000", "12345678900");
//	    dao.delete("12345678901");
//		User user=dao.selectByUnameAndUpwd("麻花疼111", "12345678");
//		System.out.println(user);
		boolean flag=dao.ajaxCheckUname("qqqqq");
		System.out.println(flag);
				
	}
}



	