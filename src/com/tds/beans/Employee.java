package com.tds.beans;

public class Employee {
	public int id;
	public String Firstname;
	public String Lastname;
	public int Badge_Number;
	public String Country_Name;
	public String Job_Title_Name;
	public String Department_Name;
	public String Start_Date;
	public String Leave_Date;
	public Employee(int id, String firstname, String lastname, int badge_Number, String country_Name,
			String job_Title_Name, String department_Name, String start_Date, String leave_Date) {
		super();
		this.id = id;
		Firstname = firstname;
		Lastname = lastname;
		Badge_Number = badge_Number;
		Country_Name = country_Name;
		Job_Title_Name = job_Title_Name;
		Department_Name = department_Name;
		Start_Date = start_Date;
		Leave_Date = leave_Date;
	}
	
}
