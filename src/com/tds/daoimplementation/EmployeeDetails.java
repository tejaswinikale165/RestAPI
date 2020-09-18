package com.tds.daoimplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tds.beans.DataConnectionDao;
import com.tds.beans.Employee;
import com.tds.dao.EmployeeDetailsDao;

//Implements EmployeeDetailsDao which contains methods provide data required by controller class
public class EmployeeDetails implements EmployeeDetailsDao {
	
	//As we are using api to access country_names, We are storing country_name in map for each call if code not present in map.
	Map <String, String>countryMap=new HashMap<String,String>();
	@Override
	
	//Returns list of all employees from employee table
	public List<Employee> getEmployees() throws SQLException, IOException {
	
		List<Employee>eList=new ArrayList<Employee>();
		
		Statement st=DataConnectionDao.getStatement();
		ResultSet rs=st.executeQuery("select emp.ID,emp.FIRSTNAME, emp.LASTNAME,emp.BADGE_NUMBER,emp.COUNTRY_CODE,jt.JOB_TITLE_Name,dept.Department_Name,emp.START_DATE,emp.LEAVE_DATE from employee emp join JOB_TITLE jt on emp.JOB_TITLE_CODE=jt.JOB_TITLE_CODE join DEPARTMENT dept on jt.DEPARTMENT_CODE=dept.DEPARTMENT_CODE");
		
		while(rs.next())
		{
			String countryCode=rs.getString("Country_Code");
			String country_name;
			String lDate=rs.getString(9);
			if (countryMap.get(countryCode)!=null)
			{
				country_name=getCountryName(countryCode);
			}
			else
			country_name=getCountryName(countryCode);
			String leaveDate= lDate==null?"null":lDate;
			eList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),country_name,rs.getString(6),rs.getString(7),rs.getString(8),leaveDate));
			
		}
		return eList;
	}

	//Returns list of employees whose leave date is not specified or not set.
	@Override
	public List<Employee> getEmployeeByStatus() throws SQLException, IOException {
		
		List<Employee>eList=new ArrayList<Employee>();
		Statement st=DataConnectionDao.getStatement();
		ResultSet rs=st.executeQuery("select emp.ID,emp.FIRSTNAME, emp.LASTNAME,emp.BADGE_NUMBER,emp.COUNTRY_CODE,jt.JOB_TITLE_Name,dept.Department_Name,emp.START_DATE,emp.LEAVE_DATE from employee emp join JOB_TITLE jt on emp.JOB_TITLE_CODE=jt.JOB_TITLE_CODE join DEPARTMENT dept on jt.DEPARTMENT_CODE=dept.DEPARTMENT_CODE where emp.LEAVE_DATE is null");
		while(rs.next())
		{
			String countryCode=rs.getString("Country_Code");
			String country_name;
			String lDate=rs.getString(9);
			if (countryMap.get(countryCode)!=null)
			{
				country_name=getCountryName(countryCode);
			}
			else
			country_name=getCountryName(countryCode);
			String leaveDate= lDate==null?"null":lDate;
			eList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),country_name,rs.getString(6),rs.getString(7),rs.getString(8),leaveDate));
			
		}
		return eList;
	}

	//Returns list of employees which belong to given departmentname
	@Override
	public List<Employee> getEmployeeByDept( String departmentName) throws SQLException, IOException {
		List<Employee>eList=new ArrayList<Employee>();
		PreparedStatement prest=DataConnectionDao.getPrepStatement("select emp.ID,emp.FIRSTNAME, emp.LASTNAME,emp.BADGE_NUMBER,emp.COUNTRY_CODE,jt.JOB_TITLE_Name,dept.Department_Name,emp.START_DATE,emp.LEAVE_DATE from employee emp join JOB_TITLE jt on emp.JOB_TITLE_CODE=jt.JOB_TITLE_CODE join DEPARTMENT dept on jt.DEPARTMENT_CODE=dept.DEPARTMENT_CODE where dept.DEPARTMENT_NAME= ?");
		prest.setString(1, departmentName);
		ResultSet rs=prest.executeQuery();
		while(rs.next())
		{
			String countryCode=rs.getString("Country_Code");
			String country_name;
			String lDate=rs.getString(9);
			if (countryMap.get(countryCode)!=null)
			{
				country_name=getCountryName(countryCode);
			}
			else
			country_name=getCountryName(countryCode);
			String leaveDate= lDate==null?"null":lDate;
			eList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),country_name,rs.getString(6),rs.getString(7),rs.getString(8),leaveDate));
			
		}
		return eList;
	}
	
	//This method access country name form api using country code passes as parameter to method
	public String getCountryName(String code) throws IOException
	{
		String url="https://restcountries.eu/rest/v2/alpha"+"/"+code;
		URL url1=new URL(url);
		HttpURLConnection conn=(HttpURLConnection)url1.openConnection();
		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb=new StringBuilder();
		String line="";
		while((line=br.readLine())!=null)
		{
			sb.append(line);
		}
		JSONObject jsonObject=new JSONObject(sb.toString());
		String countryName=jsonObject.getString("name");
		countryMap.put(code, countryName);
		return countryName;
	}
	
	//This method checks if parameter passed to method belongs to any departmentname from department table
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

}
