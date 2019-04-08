package cn.tedu.javaweb.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.sun.xml.internal.bind.v2.runtime.Name;



//���ݿ�����ӳ�:DBCP��Apache����
public class JDBC_DBCP {
	//����һ�����ݿ����Դ��
	private static BasicDataSource dataSource=null;
	
	//��ʼ�����ӳصĲ�������Ҫʹ��io�����ж�д����
	public static void init() {
		//ʵ����dataSource
		dataSource=new BasicDataSource();
		//����һ��properties����
		Properties dbProps=new Properties();
		//ʹ��io�������������ļ�
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
		
		// ����������
		if (maxIdle != null)
			dataSource.setMaxIdle(Integer.parseInt(maxIdle));

		// ��ʱ����ʱ��(�Ժ���Ϊ��λ)
		if (maxWait != null)
			dataSource.setMaxWait(Long.parseLong(maxWait));

		// ���������
		if (maxActive != null) {
			if (!maxActive.trim().equals("0"))
				dataSource.setMaxActive(Integer.parseInt(maxActive));
		}

	}
	
	//�����ݿ������
	//synchronized���߳�ͬ�����൱��һ��������ʹ��ʱ�����ܱ�������ʹ��
	public static synchronized Connection getConnection() {
		if (dataSource==null) {//�жϵ�ǰ�����ӳ��Ƿ�Ϊ��
			init();//���Ϊ�գ�Ҫ�ȳ�ʼ�����ӳ�
		}
		Connection conn=null;
		if (dataSource!=null) {//���ӳز�Ϊ��ʱ����ȡ����
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
