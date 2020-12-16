package com.yc.bbs.dao;

import java.sql.SQLException;
import java.util.List;

import com.yc.common.util.DBHelper;

public class BoardDao {

	public List<?> query(){
		String sql = "SELECT\r\n" + 
				"	a.*, b.*, c.title,\r\n" + 
				"	c.uid,\r\n" + 
				"	c.publishtime\r\n" + 
				"FROM\r\n" + 
				"	tbl_board a\r\n" + 
				"LEFT JOIN (\r\n" + 
				"	SELECT\r\n" + 
				"		boardid,\r\n" + 
				"		count(*) cnt\r\n" + 
				"	FROM\r\n" + 
				"		tbl_topic\r\n" + 
				"	GROUP BY\r\n" + 
				"		boardid\r\n" + 
				") b ON a.id = b.boardid\r\n" + 
				"LEFT JOIN (\r\n" + 
				"	SELECT\r\n" + 
				"		*\r\n" + 
				"	FROM\r\n" + 
				"		tbl_topic a\r\n" + 
				"	WHERE\r\n" + 
				"		a.id IN (\r\n" + 
				"			SELECT\r\n" + 
				"				max(id)\r\n" + 
				"			FROM\r\n" + 
				"				tbl_topic\r\n" + 
				"			GROUP BY\r\n" + 
				"				boardid\r\n" + 
				"		)\r\n" + 
				") c ON a.id = c.boardid";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
