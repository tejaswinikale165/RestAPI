package com.tds.beans;

public class Department {
	public int Department_Code;
	public String Department_Name;
	public Department(int department_Code, String department_Name) {
		super();
		Department_Code = department_Code;
		Department_Name = department_Name;
	}
	public int getDepartment_Code() {
		return Department_Code;
	}
	public void setDepartment_Code(int department_Code) {
		Department_Code = department_Code;
	}
	public String getDepartment_Name() {
		return Department_Name;
	}
	public void setDepartment_Name(String department_Name) {
		Department_Name = department_Name;
	}
	

}
