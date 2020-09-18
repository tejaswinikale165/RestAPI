package com.tds.beans;

public class Job_Title {
public int Job_Tile_Code;
public String Job_Title_Name;
public String Department_Name;
public Job_Title(int job_Tile_Code, String job_Title_Name, String department_Name) {
	super();
	Job_Tile_Code = job_Tile_Code;
	Job_Title_Name = job_Title_Name;
	Department_Name = department_Name;
}
public int getJob_Tile_Code() {
	return Job_Tile_Code;
}
public void setJob_Tile_Code(int job_Tile_Code) {
	Job_Tile_Code = job_Tile_Code;
}
public String getJob_Title_Name() {
	return Job_Title_Name;
}
public void setJob_Title_Name(String job_Title_Name) {
	Job_Title_Name = job_Title_Name;
}
public String getDepartment_Name() {
	return Department_Name;
}
public void setDepartment_Name(String department_Name) {
	Department_Name = department_Name;
}



}
