package com.yc.common.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.common.util.DBHelper;

@WebServlet("/user.s")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void query(HttpServletRequest request,
			HttpServletResponse response)
			throws SQLException,IOException{
		String sql = "select * from tbl_user";
		List<?> ret = DBHelper.selectListMap(sql);
		write(response,ret);
	}
		

}
