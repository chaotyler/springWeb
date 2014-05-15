package com.tyler.webapp.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tyler.webapp.dao.StudentDao;
import com.tyler.webapp.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	StudentDao studentDao = new StudentDao();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable long id, Model model) throws SQLException {
		Student student = studentDao.getById(id);
		model.addAttribute("student", student);
		return "student";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getAll(Model model){
		List<Student> stuList = studentDao.getAll();
		model.addAttribute("stuList", stuList);
		return "studentList";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String add(@RequestParam(value="name") String name, Model model){
		Student newStu = new Student();
		newStu.setName(name);
		studentDao.add(newStu);
		List<Student> stuList = studentDao.getAll();
		model.addAttribute("stuList", stuList);
		return "studentList";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, Model model) throws SQLException {
		studentDao.deleteById(id);
		return getAll(model);
	}
	
	
	
	
	//-----------------------------------------------------------------------------------
	//return json string, used for ajax
	@RequestMapping(value = "/{id}/json", method = RequestMethod.GET)
	@ResponseBody
	public String getJSON(@PathVariable long id) throws SQLException {
		Student student = studentDao.getById(id);
		Gson gs = new Gson();
		String stuStr = gs.toJson(student);
		return stuStr;
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public String getAllJSON(){
		List<Student> stuList = studentDao.getAll();
		Gson gs = new Gson();
		String stuListStr = gs.toJson(stuList);
		return stuListStr;
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.POST)
	@ResponseBody
	public String addJSON(@RequestParam(value="name") String name){
		Student newStu = new Student();
		newStu.setName(name);
		studentDao.add(newStu);
		List<Student> stuList = studentDao.getAll();
		Gson gs = new Gson();
		String stuListStr = gs.toJson(stuList);
		return stuListStr;
	}
	
	@RequestMapping(value = "/delete/{id}/json", method = RequestMethod.GET)
	@ResponseBody
	public String deleteJSON(@PathVariable long id) throws SQLException {
		boolean result = studentDao.deleteById(id);
		return "result: " + result;
	}
}
