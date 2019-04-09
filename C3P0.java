package cn.tedu.javaweb.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//自动回收空闲的连接到连接池中
public class C3P0 {
//定义一个C3P0的连接池
	private static ComboPooledDataSource dataSource=null;
	//使用连接池的构造方法完成配置文件的加载
	static {
		dataSource=new ComboPooledDataSource("mysql");
	}
	//获取一个数据库的连接
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//关闭一个数据库连接:把连接资源释放到连接池中
	public static void closeConnection(Connection conn) {
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		getConnection();
	}
}
