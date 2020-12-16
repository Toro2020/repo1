package com.yc.user.util;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DBHelper {

	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;
	static {
		try {
			String path ="conn.properties";
			InputStream in = DBHelper.class.getClassLoader().getResourceAsStream(path);

			Properties prop = new Properties();
			prop.load(in);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			name = prop.getProperty("name");
			pwd = prop.getProperty("pwd");
			Class.forName(driver);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,name,pwd);
	}
	
	public static <T> List<T> selectList(String sql,
			ResultSetMapper<T> callback,
			Object...params) throws SQLException{
		System.out.println("SQL"+sql);
		System.out.println("参数"+Arrays.toString(params));
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			for(int i = 0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			ResultSet rs =ps.executeQuery();
			//while(rs.next());
			List<T> ret = new ArrayList<>();
			for(;rs.next();) {
				T t =callback.map(rs);
				ret.add(t);
			}
			return ret;
		}finally{ 
			conn.close();
		}
	}

	/**
	 * 泛型接口
	 * @author 24459
	 *
	 * @param <T>
	 */
	public static interface ResultSetMapper<T>{
		T map(ResultSet rs) throws SQLException;
	}

	public static int update(String sql, Object...params) throws SQLException {
		System.out.println("SQL"+sql);
		System.out.println("参数"+Arrays.toString(params));
		Connection conn = getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			for(int i = 0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			return ps.executeUpdate();
		}finally {
			conn.close();
		}
	}
}
