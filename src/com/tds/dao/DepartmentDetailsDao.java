package com.tds.dao;

import java.sql.SQLException;
import java.util.List;

import com.tds.beans.Department;

public interface DepartmentDetailsDao {
	public List<Department> getDepartments() throws SQLException;

}
