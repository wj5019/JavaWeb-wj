package cn.tedu.javaweb.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.sun.xml.internal.bind.v2.runtime.Name;



//数据库的连接池:DBCP由Apache开发
public class JDBC_DBCP {
	//定义一个数据库的资源类
	private static BasicDataSource dataSource=null;
	
	//初始化连接池的参数：需要使用io流进行读写操作
	public static void init() {
		//实例化dataSource
		dataSource=new BasicDataSource();
		//定义一个properties的类
		Properties dbProps=new Properties();
		//使用io流，读入配置文件
		try {
			dbProps.load(JDBC_DBCP.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String driveClassName = dbProps.getProperty("jdbc.driver");
		String url = dbProps.getProperty("jdbc.url");
		String username = dbProps.getProperty("jdbc.user");
		String password = dbProps.getProperty("jdbc.password");

		String initialSize = dbProps.getProperty("dataSource.initialSize");
		String minIdle = dbProps.getProperty("dataSource.minIdle");
		String maxIdle = dbProps.getProperty("dataSource.maxIdle");
		String maxWait = dbProps.getProperty("dataSource.maxWait");
		String maxActive = dbProps.getProperty("dataSource.maxActive");
		
		dataSource.setDriverClassName(driveClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		if (initialSize != null) {
			dataSource.setInitialSize(Integer.parseInt(initialSize));
		}
		
		if (minIdle != null) {
			dataSource.setMinIdle(Integer.parseInt(minIdle));
		}
		
		// 最大空闲连接
		if (maxIdle != null)
			dataSource.setMaxIdle(Integer.parseInt(maxIdle));

		// 超时回收时间(以毫秒为单位)
		if (maxWait != null)
			dataSource.setMaxWait(Long.parseLong(maxWait));

		// 最大连接数
		if (maxActive != null) {
			if (!maxActive.trim().equals("0"))
				dataSource.setMaxActive(Integer.parseInt(maxActive));
		}

	}
	
	//打开数据库的连接
	//synchronized：线程同步，相当于一个连接在使用时，不能被其他的使用
	public static synchronized Connection getConnection() {
		if (dataSource==null) {//判断当前的连接池是否为空
			init();//如果为空，要先初始化连接池
		}
		Connection conn=null;
		if (dataSource!=null) {//连接池不为空时，获取连接
			try {
				conn=dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnection();
	}

}
