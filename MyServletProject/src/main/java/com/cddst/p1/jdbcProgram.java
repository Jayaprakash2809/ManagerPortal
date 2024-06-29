package com.cddst.p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jdbcProgram extends HttpServlet{
	private Connection connection;
	private Statement statement;
	private ResultSet res;

	@Override
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String parameter = request.getParameter("root");
		String pwd = request.getParameter("pwd");
		PrintWriter writer = response.getWriter();
		writer.println("driver loaded");
		
		connection = DriverManager.getConnection(Credentials.url, parameter, pwd);
		// TODO Auto-generated method stub
		if(connection==null) {
			writer.println("Connection not established");
		}else {
			writer.println("Connection Established");
		}
		String sql="Select * from employee_dt";
		statement = connection.createStatement();
		res = statement.executeQuery(sql);
		while(res.next()==true) {
			writer.println( "emp id    :"+ res.getInt(1));
			writer.println( "emp name :"+ res.getString(2));
			writer.println( "emp exp  :"+ res.getInt(3));
			writer.println("**************************");
			
		}
		
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}


