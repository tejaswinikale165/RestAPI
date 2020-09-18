package com.tds.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tds.beans.Employee;

public interface EmployeeDetailsDao {
	public List<Employee>getEmployees() throws SQLException, IOException;
	public List<Employee>getEmployeeByStatus() throws SQLException, IOException;
	
	public List<Employee> getEmployeeByDept(String departmentName) throws SQLException, IOException;
	boolean checkValidDepatment(String departmentName) throws SQLException;
}
