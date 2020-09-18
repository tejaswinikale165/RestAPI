package com.tds.dao;

import java.sql.SQLException;
import java.util.List;


import com.tds.beans.Badge;

public interface BadgeDetailsDao {
public List<Badge> getBadges() throws Exception;

public List<Badge> getBadgesByStatus() throws SQLException;


public Badge getBadgesByNumber(int badgeNumber) throws SQLException;
}
