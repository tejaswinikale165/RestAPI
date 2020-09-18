package com.tds.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tds.dao.DepartmentDetailsDao;
import com.tds.daoimplementation.DepartmentDetails;

//Controller for handling request related to Department.
@WebServlet(name = "DepartmentController",urlPatterns= {"/department"})
public class DepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DepartmentController() {
        super();
    }

    //Handling get request for url types /department  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Checking for authentication
		if(!("candidate".equals((String) this.getServletContext().getInitParameter("username"))&& "INTtestv1".equals((String)request.getServletContext().getInitParameter("password"))))
				{
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
		
		Gson gson = new Gson();
		
		DepartmentDetailsDao departmentDetails=new DepartmentDetails();
		try {
				String jsonDList = gson.toJson(departmentDetails.getDepartments());
				response.getWriter().print(jsonDList);
				return;
		   } 
		catch (SQLException e) {
					e.printStackTrace();
				}
	}

	

}
