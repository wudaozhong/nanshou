package com.ch0;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SurveyServlet extends HttpServlet {
	private Connection connection;
	private Statement statement;
	
	public void init( ServletConfig config)throws ServletException{
		try {
			
			connection = Connector.getConnector();
			statement = connection.createStatement();
			
		}catch ( Exception exception ) {
			exception.printStackTrace();
			throw new UnavailableException( exception.getMessage() );
		}
	}
	
	protected void doPost(HttpServletRequest request, 
		HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		DecimalFormat twodigits = new DecimalFormat( "0.00" );
		
		out.println( "<?xml version = \"1.0\"?>" );
		
		out.println( "<html xmlns = \"http://www.w3.org/1999/xhtml\"" );
		
		out.println("<head>");
		
		int value =
			Integer.parseInt( request.getParameter( "animal" ) );
		String query;
		
		try {
			query = "UPDATE surveyresults SET votes = votes + 1" + 
				"WHERE id = " + value;	
			statement.executeUpdate( query );
			
			query = "SELECT sum(votes) FROM surveyresults";
			ResultSet totalRS = statement.executeQuery( query );
			totalRS.next();
			int total = totalRS.getInt( 1 );
			
			query = "SELECT surveyoption, votes, id FROM surveyresults" +
				"ORDER BY id";
			
			ResultSet resultsRS = statement.executeQuery( query );
			
			out.println( "<title>Thank you!</title>" );
			out.println( "</head>" );
			
			out.println("<body>");
			out.println("<p>Thank you for participating.");
			out.println("<br />Results:</p><pre>");
			
			int votes;
			while( resultsRS.next() ) {
				out.println( resultsRS.getString( 1 ) );
				out.print(": ");
				votes = resultsRS.getInt( 2 );
				out.print( twodigits.format( 
					(double) votes/ total * 100 ) );
				out.print( "% responses: " );
				out.println( votes );
			}
			
			resultsRS.close();
			
			out.print("Total responses: ");
			out.println( total );
			
			out.println("</pre></body></html>");
			out.close();
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			out.println( "<title>Error</title>" );
			out.println( "</head>" );
			out.println( "<body><p>Database error occurred." );
			out.println( "Try again later.</p></body></html>" );
		}
		
	}

	public void destroy() {
		
		try {
			statement.close();
			connection.close();
		}catch (SQLException sqlException ) {
			sqlException.printStackTrace();
		}
	}
}
