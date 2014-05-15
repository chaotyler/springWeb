package com.tyler.webapp.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

	Properties prop = new Properties();
	private String url;
	private String user;
	private String password;
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

	public Statement initStat() {
		try {
			String proFilePath = getClass().getClassLoader().getResource("/")
					.getPath()
					+ "..\\db.properties";
			prop.load(new FileInputStream(proFilePath));
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();
			return stat;
		} catch (Exception e) {
			System.out.println("Database connection init failed!" + e);
			return null;
		}
	}

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("Database connection close failed!");
		}
	}
}
