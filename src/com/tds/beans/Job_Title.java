package com.tds.beans;

public class Job_Title {
public int Job_Tile_Code;
public String Job_Title_Name;
public String Departmanet_Name;
public Job_Title(int job_Tile_Code, String job_Title_Name, String departmanet_Name) {
	super();
	Job_Tile_Code = job_Tile_Code;
	Job_Title_Name = job_Title_Name;
	Departmanet_Name = departmanet_Name;
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
public String getDepartmanet_Name() {
	return Departmanet_Name;
}
public void setDepartmanet_Name(String departmanet_Name) {
	Departmanet_Name = departmanet_Name;
}



}
