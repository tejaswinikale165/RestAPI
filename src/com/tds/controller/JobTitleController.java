package com.tds.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tds.dao.JobTitleDetailsDao;
import com.tds.daoimplementation.JobTitleDetails;
//Controller for handling request related to job_titles.
@WebServlet(urlPatterns={"/job_titles","/job_titles/*"},loadOnStartup = 1)

public class JobTitleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JobTitleController() {
        super();
     }

  //Handling get request for two url types /job_titles and /job_titles/:{department_name} using switch to matching the pattern
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(!("candidate".equals((String) this.getServletContext().getInitParameter("username"))&& "INTtestv1".equals((String)request.getServletContext().getInitParameter("password"))))
			{
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		
		Gson gson = new Gson();
		
		JobTitleDetailsDao jobTitleDetails=new JobTitleDetails();
		//get uri pattern check if department name is there in uri 
		String[] pattern=request.getRequestURI().split("/");
		
		if(pattern.length>4)
			{
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		//remove colon from starting of department name
		String departmentName=pattern[pattern.length-1].substring(1);
		try {
				if(!(pattern[pattern.length-1].equals("job_titles")))
				{
					//Checking department name is valid
					if(!jobTitleDetails.checkValidDepatment(departmentName))
					{
						response.sendError(422,"Not Valid Department");
						return;
					}
		
					else
					{
						if(!jobTitleDetails.getJobTilesByDept(departmentName).isEmpty())
							{
								response.getWriter().print(gson.toJson(jobTitleDetails.getJobTilesByDept(departmentName)));
								return;
							}
						else
						{
							response.sendError(HttpServletResponse.SC_NOT_FOUND,"No Job_Title in"+departmentName);
							return;
						}
					}
					
				}
				else
				{
					if(pattern[2].equals("job_titles"))
						{
							response.getWriter().print(gson.toJson(jobTitleDetails.getJobTitles()));
							return;
						}
					else {
							response.sendError(HttpServletResponse.SC_NOT_FOUND);
							return;
						}
				}
		    } 
		catch (IOException e) {
				e.printStackTrace();
			}
		catch (SQLException e) {
				e.printStackTrace();
			}
	}



}
