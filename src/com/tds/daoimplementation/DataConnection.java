package com.tds.daoimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tds.beans.DataConnectionDao;

//It is servlet context listener created to create database connection instance when application is up
@WebListener
public class DataConnection implements ServletContextListener {
	
	// Method takes context paraments and pass it to createConnection method
	public void contextInitialized(ServletContextEvent event) 
	{
		
		ServletContext context=event.getServletContext();
		String url=context.getInitParameter("url");
		String username=context.getInitParameter("username");
		String password=context.getInitParameter("password");
		
		createConnection(url,username,password);
		
	}
	//Method creates connection and set it to use it later
	public void createConnection(String url,String username,String password)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection(url,username,password);
			DataConnectionDao.setConn(conn);
			DataConnectionDao.setStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute("url");
        servletContext.removeAttribute("username");
        servletContext.removeAttribute("password");
    }
}
