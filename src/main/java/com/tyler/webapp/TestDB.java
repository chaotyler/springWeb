package com.tyler.webapp;

import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {

	public static void main(String[] args) {
		// old db connection
		// String driver = "com.mysql.jdbc.Driver";
		//
		// String url = "jdbc:mysql://127.0.0.1:3306/springweb";
		//
		// String user = "root";
		//
		// String password = "root";
		//
		// try {
		// Class.forName(driver);
		// Connection conn = DriverManager.getConnection(url, user, password);
		// Statement statement = conn.createStatement();
		// String sql = "select * from student";
		// ResultSet rs = statement.executeQuery(sql);
		// Student s = null;
		// while(rs.next()) {
		// s = new Student();
		// s.setId(rs.getLong(1));
		// s.setName(rs.getString(2));
		// System.out.println(s.speak());
		// }
		// rs.close();
		// conn.close();
		// }catch(Exception e){
		// System.out.println(e);
		// }
		
		DatabaseConnection dc = new DatabaseConnection();
		dc.init();
		Statement st = dc.getStat();
		try {
//			query db
			String sql = "select * from student";
			ResultSet rs = st.executeQuery(sql);
			Student s = null;
			while (rs.next()) {
				s = new Student();
				s.setId(rs.getLong(1));
				s.setName(rs.getString(2));
				System.out.println(s.speak());
			}
			rs.close();

			
//			insert
//			Student s = new Student();
//			s.setName("Chao");
//			String sql = "insert into student set name = '" + s.getName() + "'";
//			st.execute(sql);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		dc.close();
	}
}