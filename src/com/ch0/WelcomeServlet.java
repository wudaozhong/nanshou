package com.ch0;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	protected void doGet( HttpServletRequest request, 
			HttpServletResponse response ) 
			throws ServletException, IOException{
			
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter();
			
			// send XHTML page to client
			//start XHTML document
			out.println("<?xml version = \"1.0\"?>");		
			
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD "
					+ "XHTML 1.0 Strict//EN\" \"http://www.w3.org"
					+ "/TR/xhtml/DTD/xhtml1-strict.dtd\"");
			
			out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");
			
			out.println("<head>");
			out.println("<title>A simple Servlet Example</title>");
			out.println("</head>");
			
			out.println("<body>");
			out.println("<h1>Welcome to Servlets!</h1>");
			out.println("</body>");
			
			out.println("</html>");
			out.close();
			
		}

}
