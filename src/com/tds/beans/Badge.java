package com.tds.beans;


public class Badge {
 public int Badge_Number;
 public String Badge_Status;
 public String Badge_Expiry_Date;
public Badge(int badge_Number, String badge_Status, String badge_Expiry_Date) {
	super();
	Badge_Number = badge_Number;
	Badge_Status = badge_Status;
	Badge_Expiry_Date = badge_Expiry_Date;
}
public int getBadge_Number() {
	return Badge_Number;
}
public void setBadge_Number(int badge_Number) {
	Badge_Number = badge_Number;
}
public String getBadge_Status() {
	return Badge_Status;
}
public void setBadge_Status(String badge_Status) {
	Badge_Status = badge_Status;
}
public String getBadge_Expiry_Date() {
	return Badge_Expiry_Date;
}
public void setBadge_Expiry_Date(String badge_Expiry_Date) {
	Badge_Expiry_Date = badge_Expiry_Date;
}

}
