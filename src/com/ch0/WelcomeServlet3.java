package com.ch0;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet3 extends HttpServlet {

 
	protected void doPost(HttpServletRequest request, 
		HttpServletResponse response) 
				throws ServletException, IOException {
		
		String firstName = request.getParameter( "firstname" );
		
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		
		out.println( "<?xml version = \"1.0\"?>" );
		out.println( "<html xmlns = \"http://www.w3.org/1999/xhtnl\">" );
		out.println( "<head>" );
		out.println( "<title>Processing post requests with data</title>" );
		out.println( "</head>" );
		
		out.println( "<body>" );
		out.println( "<h1>Hello " + firstName + ",<br />" );
		out.println( "Welcome to Servlets!</h1>" );
		out.println( "</body>" );
		
		out.println( "</html>" );
		out.close();
		
	}

}
