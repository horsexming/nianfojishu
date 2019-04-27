package com.task.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class JDBCUtils {
	private final static String url="jdbc:sqlserver://192.168.0.246:1433;databaseName=toolsManager";
	private final static String username="sa";
	private final static String password="linju2014";
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
}
