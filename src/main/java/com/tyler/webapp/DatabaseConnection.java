package com.tyler.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
	private String driver = "com.mysql.jdbc.Driver";

	private String url = "jdbc:mysql://127.0.0.1:3306/springweb";

	private String user = "root";

	private String password = "root"; 
	
	private Connection conn;
	
	private Statement stat;
	
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStat() {
		return stat;
	}

	public void setStat(Statement stat) {
		this.stat = stat;
	}

	public void init(){
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();
		}catch(Exception e){
			System.out.println("Database connection init failed!");
		}
	}
	
	public void close(){
		try{
			conn.close();
		}catch(Exception e){
			System.out.println("Database connection close failed!");
		}
	}
	
//	public Statement getStatement(){
//		try{
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
//			Statement statement = conn.createStatement();
//			String sql = "select * from student";
//			ResultSet rs = statement.executeQuery(sql);
//			Student s = null;
//			while(rs.next()) { 
//				s = new Student();
//				s.setId(rs.getLong(1));
//				s.setName(rs.getString(2));
//				System.out.println(s.speak());
//			}
//			rs.close();
//			conn.close();
//			return statement;
//		
//		}catch(Exception e){
//			return null;
//		}
//	}
}
