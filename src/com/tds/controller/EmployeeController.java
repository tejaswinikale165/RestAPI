package com.tds.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tds.dao.EmployeeDetailsDao;
import com.tds.daoimplementation.EmployeeDetails;
//Controller for handling request related to employess.
@WebServlet(
		urlPatterns = { 
				"/employees", 
				"/employees/active"
		})
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeController() {
        super();
       
    }
    //Handling get request for two url types /employees,/employee?department_name='value' and /employees/active using switch to matching the pattern
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Checking for authentication, this parameter is set in servlet listener when connect to database
		if("false".equals((String) this.getServletContext().getInitParameter("authorized")))
			{
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid username or password");
				return;
			}
			
		try {
			Gson gson = new Gson();
			EmployeeDetailsDao employeeDetails=new EmployeeDetails();
	     
			String path = request.getServletPath();
	    
			switch(path)
			{
				case "/employees":
					String param=request.getParameter("department_name");
					Enumeration<String> paraName=request.getParameterNames();
					
					while(paraName.hasMoreElements())
					{
						if(!paraName.nextElement().equals("department_name"))
							{
								response.sendError(422,"Invalid Query Parameter");
								return;
							}
					}
					
					if (param==null)
						{
							response.getWriter().print(gson.toJson(employeeDetails.getEmployees()));
							response.setStatus(HttpServletResponse.SC_OK);
							return;
						}
					else 
						{
							if(employeeDetails.checkValidDepatment(param))
							{
								if(!(employeeDetails.getEmployeeByDept(param).isEmpty()))
								
									{
										response.getWriter().print(gson.toJson(employeeDetails.getEmployeeByDept(param)));
										response.setStatus(HttpServletResponse.SC_OK);
										return;
									}
								else
									{
										response.sendError(HttpServletResponse.SC_NOT_FOUND,"No Employees in "+param);
										return;
									}
							}
							else
								{
									response.sendError(422,"Invalid Department");
									return;
								}
						
						}
				
				case "/employees/active":
	    	 
					if(employeeDetails.getEmployeeByStatus().isEmpty())
						{
							response.sendError(HttpServletResponse.SC_NOT_FOUND,"No Active Employees");
							return;
						}
					else
						{
							response.getWriter().print(gson.toJson(employeeDetails.getEmployeeByStatus()));
							response.setStatus(HttpServletResponse.SC_OK);
							return;
						}
				
			}

	     } 
		catch (SQLException e) {
				
				e.printStackTrace();
			} 
		catch (Exception e) {
	
			e.printStackTrace();
		}
	}

}
