package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.EmployeesJDBCTemplate;
import com.test.domain.Employees;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private EmployeesJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Employees> list = jdbcTemplate.departmentList();
		
		model.addAttribute("list", list);
		
		return "department/departmentlist"; ///WEB-INF/views/department/departmentlist.jsp
	}
	
}
