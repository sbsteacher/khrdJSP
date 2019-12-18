package com.dh.mh.db;

import java.sql.*;

public class DBCon {
	public static Connection getCon() throws Exception {
		Connection con = null;		
		Class.forName("com.mysql.jdbc.Driver");
		final String URL = "jdbc:mysql://localhost:3306/customer_manage?useSSL=true";
		final String USER = "root";
		final String PW = "rootroot";
		
		con = DriverManager.getConnection(URL, USER, PW);
		System.out.println("접속 성공!!!!");		
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}
	
	public static void close(
			Connection con, PreparedStatement ps, ResultSet rs) {
		
		if(rs != null){
			try { rs.close(); } 
			catch (SQLException e) {				
				e.printStackTrace();				
			}
		}
		
		if(ps != null) {
			try { ps.close(); }
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con != null) {
			try { con.close(); }
			catch (SQLException e) {
				e.printStackTrace();
			}
		}	

	}
}
