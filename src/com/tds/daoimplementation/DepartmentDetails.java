package com.tds.daoimplementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tds.beans.DataConnectionDao;
import com.tds.beans.Department;
import com.tds.dao.DepartmentDetailsDao;

//Implements DepartmentDetailsDao which contains methods provide data required by controller class
public class DepartmentDetails implements DepartmentDetailsDao{
	
	// Returns list of all departments fetched from department table
	public List<Department> getDepartments() throws SQLException
	{
		List<Department>Dlist=new ArrayList<Department>();
		Connection conn=DataConnectionDao.getConn();
		
		Statement st=conn.createStatement();
	
		ResultSet rs=st.executeQuery("select * from Department");
		while(rs.next())
		{
			Dlist.add(new Department(rs.getInt(1),rs.getString(2)));
		}
		return Dlist;
	}

}
