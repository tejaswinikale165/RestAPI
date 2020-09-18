package com.tds.daoimplementation;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.tds.beans.Badge;
import com.tds.beans.DataConnectionDao;
import com.tds.dao.BadgeDetailsDao;

//Implements BadgeDetailsDao which contains methods provide data required by controller class
public class BadgeDetails implements BadgeDetailsDao {
	// Returns list of all badges fetched from badge table
	@Override
	public List<Badge> getBadges() throws Exception {
		List<Badge>bList=new ArrayList<Badge>();
		
		
		Statement st=DataConnectionDao.getStatement();
	
		ResultSet rs=st.executeQuery("select * from Badge");
		while(rs.next())
		{
			bList.add(new Badge(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return bList;
		
	}
	//Returns list of badges whose status is active and expiry_date is not in past
	@Override
	public List<Badge> getBadgesByStatus() throws SQLException {
		List<Badge>bList=new ArrayList<Badge>();
		
		
		String pattern = "dd-MMM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
	
		
		PreparedStatement st=DataConnectionDao.getPrepStatement("select * from Badge where Badge_Status=? and Badge_Expiry_Date >= to_date(?)");
		//ResultSet rs=st.executeQuery("select * from Badge where Badge_Status='Active' and Badge_Expiry_Date >= to_date('"+date+"')");
		st.setString(1, "Active");
		st.setString(2, date);
		ResultSet rs=st.executeQuery();
		while(rs.next())
		{
			bList.add(new Badge(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return bList;
	}
	//Returns the badge whose number matches with badgeNumber passed as parameter
	@Override
	public Badge getBadgesByNumber(int badgeNumber) throws SQLException {

		PreparedStatement st=DataConnectionDao.getPrepStatement("select * from Badge where Badge_Number=?");
		st.setInt(1, badgeNumber);
		ResultSet rs=st.executeQuery();
		
		if(rs.next())
		return new Badge(rs.getInt(1),rs.getString(2),rs.getString(3));
		else
		return null;}

}
