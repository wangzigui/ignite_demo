package com.test.ignite_server_project;

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
import java.util.UUID;

public class IgniteClient {

	public static List<Map<String, Object>> execQuery(String sql, Object[] params) {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:ignite:thin://10.71.21.177/");

			PreparedStatement stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i] + "");
				}
			}

			ResultSet rs = stmt.executeQuery();

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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}
		return null;
	}
	
	public static int execUpdate(String sql, Object[] params) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			NewProxyConnection
			con =  DriverManager.getConnection("jdbc:ignite:thin://10.71.21.177/");// 获得连接对象
			pstmt = con.prepareStatement(sql);// 获得预设语句对象

			if (params != null) {
				// 设置参数列表
				for (int i = 0; i < params.length; i++) {
					// 因为问号参数的索引是从1开始，所以是i+1，将所有值都转为字符串形式，好让setObject成功运行
					pstmt.setObject(i + 1, params[i]+"");
				}
			}

			return pstmt.executeUpdate(); // 执行更新，并返回影响行数

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(con!=null)
			{
				con.close();
			}
			
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		return 0;
	}
	
	public static void main(String[]args) {
		// 删除表
//		String sql1 = "drop table Busi_trace f";
//		try {
//			IgniteDBUtil.execUpdate(sql1,null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// 创建表
//		String cratesql = "CREATE TABLE Busi_trace (" +
//	    " id varchar(36) primary key,trace_id varchar(36),business_type varchar(10),step_id varchar(20),data varchar(500),time varchar(30) ) " +
//	    " WITH \"template=replicated\"";
//		try {
//			IgniteDBUtil.execUpdate(cratesql,null);
//		} catch (Exception e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// 插入数据
		int i =0;
		long time = System.currentTimeMillis();
		while(true)
		{
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql = "insert into Busi_trace(id,trace_id,business_type,step_id,data,time) values(?,?,?,?,?,?)";
			try {
				IgniteDBUtil.execUpdate(sql,new Object[]{UUID.randomUUID().toString(),"8889","8889","8889","8889","8889"});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i>=100)
			{
				System.out.println(System.currentTimeMillis()-time);
				break;
			}
		}
		
		
		// 删除数据
//		String sql = "delete from Busi_trace f";
//		try {
//			IgniteDBUtil.execUpdate(sql,null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<Map<String,Object>>list = IgniteDBUtil.execQuery("select * from Busi_trace", null);
//		
//		
//		if(list!=null&&list.size()>0) {
//			for(Map<String,Object> sss:list)
//			{
//				System.out.println(sss.toString());
//			}
//			
//    	}
		
		// 查询数据
		while(true)
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Map<String,Object>>list = IgniteDBUtil.execQuery("select count(*) as count from Busi_trace f ", null);
			
			if(list!=null&&list.size()>0)
			{
				System.out.println(list.get(0).get("COUNT"));
			}
			
//			List<Map<String,Object>>list1 = IgniteDBUtil.execQuery("select * from Busi_trace f order by time desc", null);
//			
//			if(list1!=null&&list1.size()>0)
//			{
//				for(Map<String,Object> map :list1)
//				{
//					System.out.println(map.toString());
//				}
//			}
		}
		
	}
}
