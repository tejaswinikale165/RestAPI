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
		//Checking for authentication, this parameter is set in servlet listener when connect to database
		if("false".equals((String) this.getServletContext().getInitParameter("authorized")))
				{
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid username or password");
					return;
				}
		
		Gson gson = new Gson();
		
		DepartmentDetailsDao departmentDetails=new DepartmentDetails();
		try {
				String jsonDList = gson.toJson(departmentDetails.getDepartments());
				response.getWriter().print(jsonDList);
				response.setStatus(HttpServletResponse.SC_OK);
				return;
		   } 
		catch (SQLException e) {
					e.printStackTrace();
				}
	}

	

}
