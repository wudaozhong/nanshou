package com.ch0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://122.112.236.124:3306/electronic_fence";
	private static String user = "root";
	private static String password = "123456";
	private static Connection conn = null;
	
	public static Connection getConnector() {
		
		try {
			Class.forName( driver );
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
