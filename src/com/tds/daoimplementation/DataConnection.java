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
		
		createConnection(url,username,password,event);
		
	}
	//Method creates connection and set it to use it later
	public void createConnection(String url,String username,String password,ServletContextEvent event)
	{
		ServletContext context=event.getServletContext();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection(url,username,password);
			DataConnectionDao.setConn(conn);
			DataConnectionDao.setStatement();
			context.setInitParameter("authorized", "true");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		 catch (SQLException e) {
			if(e.getErrorCode()==1017) {
				context.setInitParameter("authorized", "false");
			}
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
