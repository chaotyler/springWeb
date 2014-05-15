package com.tyler.webapp.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tyler.webapp.model.Student;
import com.tyler.webapp.util.DatabaseConnection;

public class StudentDao {

	DatabaseConnection dc = new DatabaseConnection();

	public Student getById(long id) {
		try {
			Statement st = dc.initStat();
			String sql = "select * from student where id = " + id;
			ResultSet rs = st.executeQuery(sql);
			Student student = new Student();
			while (rs.next()) {
				student.setId(rs.getLong(1));
				student.setName(rs.getString(2));
			}
			rs.close();
			dc.close();
			return student;
		} catch (Exception e) {
			System.out.println("StudentDao -> getById(): " + e);
			return null;
		}
	}
	
	public List<Student> getAll(){
		List<Student> stuList = new ArrayList<Student>();
		try {
			Statement st = dc.initStat();
			String sql = "select * from student";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getLong(1));
				student.setName(rs.getString(2));
				stuList.add(student);
			}
			rs.close();
			dc.close();
			return stuList;
		} catch (Exception e) {
			System.out.println("StudentDao -> getAll(): " + e);
			return null;
		}
	}

	public void add(Student newStu) {
		try {
			Statement st = dc.initStat();
			String sql = "insert into student set name = '" + newStu.getName() + "'";
			st.execute(sql);
			dc.close();
		} catch (Exception e) {
			System.out.println("StudentDao -> add(): " + e);
		}
	}

	public boolean deleteById(long id) {
		try {
			Statement st = dc.initStat();
			String sql = "delete from student where id = " + id;
			st.execute(sql);
			dc.close();
			return true;
		} catch (Exception e) {
			System.out.println("StudentDao -> add(): " + e);
			return false;
		}
	}
}
