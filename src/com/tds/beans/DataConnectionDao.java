package com.tds.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataConnectionDao {
	
	static Connection conn;
	static Statement statement;
	static PreparedStatement prepStatement;
	public static PreparedStatement getPrepStatement() {
		return prepStatement;
	}
	public static PreparedStatement getPrepStatement(String sql) {
		try {
			DataConnectionDao.prepStatement = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return DataConnectionDao.prepStatement;
	}
	public static Statement getStatement() {
		return statement;
	}

	public static void setStatement() {
		try {
			DataConnectionDao.statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataConnectionDao(Connection conn){
		DataConnectionDao.conn=conn;
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		DataConnectionDao.conn = conn;
	}
	
	
	

}
