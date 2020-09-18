package com.tds.dao;

import java.sql.SQLException;
import java.util.List;

import com.tds.beans.Job_Title;;

public interface JobTitleDetailsDao {
	public List<Job_Title> getJobTitles() throws SQLException;

	public boolean checkValidDepatment(String departmentName) throws SQLException;

	public List<Job_Title> getJobTilesByDept(String departmentName)throws SQLException;

}
