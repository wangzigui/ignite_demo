package com.test.ignite_server_project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;


public class IgniteDBUtil {

	static Logger logger = Logger.getLogger(IgniteDBUtil.class.getName());
	
	private static String igniteIp;



	// 从连接池中取用一个连接
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
			
			//10.71.21.177
			return DriverManager.getConnection("jdbc:ignite:thin://172.16.33.50/");

		} catch (Exception e) {
			logger.error("Exception in C3p0Utils!", e);
			throw new Exception("数据库连接出错!", e);
		}
	}

	// 释放连接回连接池
	public static void close(Connection conn, PreparedStatement pst, ResultSet rs) throws Exception {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Exception in C3p0Utils!", e);
				throw new Exception("数据库连接关闭出错!", e);
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				logger.error("Exception in C3p0Utils!", e);
				throw new Exception("数据库连接关闭出错!", e);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Exception in C3p0Utils!", e);
				throw new Exception("数据库连接关闭出错!", e);
			}
		}
	}

	public static List<Map<String, Object>> execQuery(String sql, Object[] params) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i] + "");
				}
			}

			rs = stmt.executeQuery();

			List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();

			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();

			while (rs.next()) {
				Map<String, Object> hm = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String columnName = rsmd.getColumnName(i + 1);
					Object columnValue = rs.getObject(columnName);
					hm.put(columnName, columnValue);
				}
				al.add(hm);
			}

			return al;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public static int execUpdate(String sql, Object[] params) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// NewProxyConnection
			con = getConnection();// 获得连接对象
			pstmt = con.prepareStatement(sql);// 获得预设语句对象

			if (params != null) {
				// 设置参数列表
				for (int i = 0; i < params.length; i++) {
					// 因为问号参数的索引是从1开始，所以是i+1，将所有值都转为字符串形式，好让setObject成功运行
					pstmt.setObject(i + 1, params[i] + "");
				}
			}

			return pstmt.executeUpdate(); // 执行更新，并返回影响行数

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, pstmt, null);
		}
		return 0;
	}
}
