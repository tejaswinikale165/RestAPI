package com.tds.controller;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tds.beans.Badge;
import com.tds.dao.BadgeDetailsDao;
import com.tds.daoimplementation.BadgeDetails;
//Controller for handling request related to badges.

@WebServlet(urlPatterns={"/badges","/badges/active"})
public class BadgeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BadgeController() {
        super();
        
    }
    //Handling get request for two url types /badges, /badges?badge_number='value' and /badges/active using switch to matching the pattern 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Checking for authentication
		if(!("candidate".equals((String) this.getServletContext().getInitParameter("username"))&& "INTtestv1".equals((String)request.getServletContext().getInitParameter("password"))))
			{
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		try {
			Gson gson = new Gson();
			BadgeDetailsDao badgeDetails=new BadgeDetails();
			//taking servlet path to check with pattern matching and executing valid code
			String path = request.getServletPath();
	    
			switch(path)
			{
				
				case "/badges":
					String param=request.getParameter("badge_number");
					if (param==null)
						{
							response.getWriter().print(gson.toJson(badgeDetails.getBadges()));
							return;
						}
					else 
						{
							try {
									//Parsing string to int if parameter value is not integer it will 
									//throw numberformat exception which will give http error Badge_Number Format Invalid
									int intparam=Integer.parseInt(param);
									Badge jsonBadge=badgeDetails.getBadgesByNumber(intparam);
									if(jsonBadge==null)
										{
											response.sendError(HttpServletResponse.SC_NOT_FOUND,"No Badge Matched");
											return;
										}
									else
										{
											response.getWriter().print(gson.toJson(jsonBadge));
											return;
										}
									}
							catch(NumberFormatException ex )
							 {
								response.sendError(422,"Badge_Number Format Invalid");
								return;
							 }
						}
				case "/badges/active":
					//Displays result who has status active
					if(badgeDetails.getBadgesByStatus().isEmpty())
						{
							response.sendError(HttpServletResponse.SC_NOT_FOUND,"No Badges Are Active");
							return;
						}
					else
						{
							response.getWriter().print(gson.toJson(badgeDetails.getBadgesByStatus()));
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
