package cn.tedu.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ר�����ڷ������ݿ⣬�򿪺͹ر����ݿ������
public class JDBC {
//�Զ��巽���������ݿ������
	public static Connection getConnection() {
//����һ�����ݿ�����
		Connection conn=null;
		//����mysql��������
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
		//�������ݿ�����
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
