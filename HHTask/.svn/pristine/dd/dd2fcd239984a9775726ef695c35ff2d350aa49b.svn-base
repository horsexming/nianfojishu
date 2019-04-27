package com.task.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBCUtilHS {
	private final static String url="jdbc:sqlserver://192.168.0.103:1433;databaseName=AttDB";
	private final static String username="sa";
	private final static String password="123";
	static{
		try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}catch(ClassNotFoundException e){
				e.printStackTrace();
		}
	}
	
	public static Connection getConn()throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	
	
	public static void close(ResultSet rs,PreparedStatement st,Connection conn){
		try{
			if(rs!=null){
				rs.close();
			}
			if(st!=null){
				st.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**适合insert,update,delete
	 * @param sql
	 * @param params 为sql中"?"对应的值
	 * */
	public static boolean update(String sql,Object params[]) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean b = true;
		try{
			conn = getConn();
			stmt = conn.prepareStatement(sql);
			for (int i = 0;params!=null && i < params.length; i++) {
				stmt.setObject(i+1, params[i]);
			}
			stmt.executeUpdate();
		}catch (Exception e) {
			b = false;
		}finally{
			close(stmt);
			close(conn);
		}
		return b;
	}
	public static List<Map<String, Object>> select(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			stmt = conn.createStatement();
			System.out.println("stmt:"+stmt.toString());
			rs = stmt.executeQuery(sql);
			return returnResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			close(stmt);
			close(rs);
			close(conn);
		}
	}
	private static List<Map<String, Object>> returnResult(ResultSet rs)
			throws SQLException {
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while(rs.next()){
			map = new HashMap<String, Object>();
			for (int i = 1; i <= count; i++) {
				map.put(rsmd.getColumnName(i), rs.getObject(i));
			}
			list.add(map);
		}
		return list;
	}
	public static void close(Statement stmt) {
		if (stmt != null)
			try {
				stmt.close();
				stmt = null;
			} catch (Exception e) {
			}
	}

	public static void close(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
				rs = null;
			} catch (Exception e) {
			}
	}
	public static void close(Connection con) {
		if (con != null)
			try {
				con.close();
				con = null;
			} catch (Exception e) {
			}
	}
}
