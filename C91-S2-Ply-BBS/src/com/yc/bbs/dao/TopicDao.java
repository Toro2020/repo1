package com.yc.bbs.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.common.util.DBHelper;

public class TopicDao {

	public List<Map<String,Object>> selectByBid(String bid) throws SQLException {
		String sql = "SELECT\r\n" + 
				"	*\r\n" + 
				"FROM\r\n" + 
				"	tbl_topic a\r\n" + 
				"LEFT JOIN tbl_user b ON a.uid = b.id\r\n" + 
				"LEFT JOIN (\r\n" + 
				"	SELECT\r\n" + 
				"		topicid,\r\n" + 
				"		count(*) cnt\r\n" + 
				"	FROM\r\n" + 
				"		tbl_reply\r\n" + 
				"	WHERE\r\n" + 
				"		topicid = 1\r\n" + 
				"	GROUP BY\r\n" + 
				"		topicid\r\n" + 
				") c ON a.id = c.topicid\n"
				+" where a.boardid=?";
		return DBHelper.selectListMap(sql, bid);
	}

	public List<?> selectByIdWithReply(String id) throws SQLException {
		String sql = "SELECT\r\n" + 
				"	*\r\n" + 
				"FROM\r\n" + 
				"	(\r\n" + 
				"		SELECT\r\n" + 
				"			*\r\n" + 
				"		FROM\r\n" + 
				"			tbl_topic\r\n" + 
				"		WHERE\r\n" + 
				"			id = ?\r\n" + 
				"		UNION ALL\r\n" + 
				"			SELECT\r\n" + 
				"				*\r\n" + 
				"			FROM\r\n" + 
				"				tbl_reply\r\n" + 
				"			WHERE\r\n" + 
				"				topicid = ?\r\n" + 
				"	) a\r\n" + 
				"JOIN tbl_user b ON a.uid = b.id";
		return DBHelper.selectListMap(sql, id, id);
	}
	
	public void insert(String title,String content,
			Object uid,String boardid)throws SQLException{
		String sql = "insert into tbl_topic values(null,?,?,now(),null,?,?)";
		DBHelper.update(sql, title,content,uid,boardid);
	}
	

}
