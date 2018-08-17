package com.ch0;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RedirectServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) 
			throws ServletException, IOException {
		
		String location = request.getParameter( "page" );
		
		if ( location != null ) 
			
			if ( location.equals( "deitel" ) )
				response.sendRedirect("http://www.deitel.com¡±");
			else
				if ( location.equals( "welcome1" ) )
					response.sendRedirect( "welcome1" );
		
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		
		out.println( "<?xml version = \"1.0\"?>" );
		out.println( "<html xmlns = \"http://www.w3.org/1999/xhtnl\">" );
		out.println( "<head>" );
		out.println( "<title>Invalid page</title>" );
		out.println( "</head>" );
		
		out.println( "<body>" );
		out.println( "<h1>Invalid page requested</h1>" );
		out.println( "<p><a href = " +
			"\"/jhtp5/RedirectServlet.html\">");
		out.println("Click here to choose again</a></p>");
		out.println( "</body>" );
		
		out.println( "</html>" );
		out.close();
	}

}
