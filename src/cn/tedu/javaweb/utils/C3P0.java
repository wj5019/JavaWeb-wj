package cn.tedu.javaweb.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//�Զ����տ��е����ӵ����ӳ���
public class C3P0 {
//����һ��C3P0�����ӳ�
	private static ComboPooledDataSource dataSource=null;
	//ʹ�����ӳصĹ��췽����������ļ��ļ���
	static {
		dataSource=new ComboPooledDataSource("mysql");
	}
	//��ȡһ�����ݿ������
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
	//�ر�һ�����ݿ�����:��������Դ�ͷŵ����ӳ���
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
