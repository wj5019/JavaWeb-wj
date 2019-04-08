package cn.tedu.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//专门用于访问数据库，打开和关闭数据库的连接
public class JDBC {
//自定义方法：打开数据库的连接
	public static Connection getConnection() {
//定义一个数据库连接
		Connection conn=null;
		//加载mysql的驱动包
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/teduweb?characterEncoding=utf-8";
			conn=DriverManager.getConnection(url,"root","12345678");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回数据库连接
		return conn;
	}
	public static void main(String[] args) {
		Connection conn=getConnection();
		try {
			boolean flag=conn.isClosed();
			System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
