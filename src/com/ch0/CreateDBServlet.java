package com.ch0;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDBServlet extends HttpServlet {

	private String url;
	private String user;
	private String password;

	public void init() throws ServletException {
		String driverClass = getInitParameter("com.mysql.jdbc.Driver");
		url = getInitParameter( "jdbc:mysql://localhost:3306/mysql" );
		user = getInitParameter( "root" );
		password = getInitParameter( "080923" );

		try {
			Class.forName( driverClass );
		} catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			throw new ServletException("�������ݿ�����ʧ�ܣ�");
		}
	}
	
	public void doGet(HttpServletRequest request,
		HttpServletResponse response )
			throws ServletException, IOException{
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection( url, user, password );
			statement = connection.createStatement();
			statement.executeUpdate( "create database bookstore" );
			statement.executeUpdate( "use bookstore" );
			statement.executeUpdate( "create table bookinfo(id INT not null"
				+ "primary key,title VARCHAR(50) not null,author VARCHAR(50) "
				+ "not null,bookconcern VARCHAE(100) not null,"
				+ "publish_date DATE not null,price FLOAT(4,2) not null,"
				+ "amount SMALLINT,remark VARCHAR(200))ENGINE = InnoDB" );
			statement.addBatch("insert into bookinfo values(1,'Java Web�������',"
					+ "'����','���ӹ�ҵ������','2006-4-20',99.00,35,null)");
			statement.addBatch("insert into bookinfo values(2,'Struts 2�������',"
					+ "'����','���ӹ�ҵ������','2008-6-15',79.00,20,null)");
			statement.addBatch("insert into bookinfo values(3,'Servlet/JSP�������',"
					+ "'����','���ӹ�ҵ������','2008-7-1',79.00,10,null)");
			statement.executeBatch();
			
			response.setContentType( "text/html;charset=GBK" );
			PrintWriter out = response.getWriter();
			out.println( "���ݿⴴ���ɹ���" );
			out.close();
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new ServletException( se );
		}
		finally {
			if ( statement != null ) {
				try {
					statement.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
				statement = null;
			}
			if ( connection != null ) {
				try {
					connection.close();
				} catch (SQLException ce) {
					// TODO Auto-generated catch block
					ce.printStackTrace();
				}
				connection = null;
			}	
			
		}
		
	}
}
