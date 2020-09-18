package com.tds.daoimplementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tds.beans.DataConnectionDao;
import com.tds.beans.Job_Title;
import com.tds.dao.JobTitleDetailsDao;

//Implements JobTitleDetailsDao which contains methods provide data required by controller class
public class JobTitleDetails implements JobTitleDetailsDao {

	// Returns list of all job_titles fetched from job_title table
	@Override
	public List<Job_Title> getJobTitles() throws SQLException {
		List<Job_Title>jList=new ArrayList<Job_Title>();
		
		Statement st=DataConnectionDao.getStatement();
	
		ResultSet rs=st.executeQuery("select jt.Job_Title_Code, jt.Job_Title_Name,dept.DEPARTMENT_NAME from Job_Title jt join Department dept on jt.Department_Code=dept.Department_Code");
		while(rs.next())
		{
			jList.add(new Job_Title(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return jList;
	}

	//This method checks if passed argument departmentname belongs any department from department table
	//if yes then it returns true else returns false
	@Override
	public boolean checkValidDepatment(String departmentName) throws SQLException {
		Statement st=DataConnectionDao.getStatement();
		List<String>dList=new ArrayList<String>();
		ResultSet rs=st.executeQuery("select distinct(Department_Name) from department");
		while(rs.next())
		{
			dList.add(rs.getString(1));
		}
		
		return dList.contains(departmentName);
	}

	//returns list of all job_tiles that belongs to departmentname give as argument to method
	@Override
	public List<Job_Title> getJobTilesByDept(String departmentName)throws SQLException {
		
		List<Job_Title>jList=new ArrayList<Job_Title>();
		String query="select jt.Job_Title_Code,jt.Job_Title_Name,dept.Department_Name from job_title jt join Department dept on jt.Department_code=dept.Department_Code where dept.Department_Name=?";
		PreparedStatement preSt=DataConnectionDao.getPrepStatement(query);
		preSt.setString(1, departmentName);
		ResultSet rs=preSt.executeQuery();
		while(rs.next())
		{
			jList.add(new Job_Title(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return jList;
		
	}

}
