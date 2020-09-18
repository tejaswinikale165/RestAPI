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
		//Checking for authentication
		if(!("candidate".equals((String) this.getServletContext().getInitParameter("username"))&& "INTtestv1".equals((String)request.getServletContext().getInitParameter("password"))))
			{
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
							return;
						}
					else 
						{
							if(employeeDetails.checkValidDepatment(param))
							{
								if(!(employeeDetails.getEmployeeByDept(param).isEmpty()))
								
									{
										response.getWriter().print(gson.toJson(employeeDetails.getEmployeeByDept(param)));
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
