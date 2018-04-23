package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.EmployeesJDBCTemplate;
import com.test.domain.Employees;

@Controller
@RequestMapping("/employee")
public class EmployeesController {
	
	@Autowired
	private EmployeesJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/list")
	public String list(Model model, String key, String value) {
		
		List<Employees> list = null;
		
		System.out.println(key);
		System.out.println(value);
		
		if (key == null && value == null) {
			list = this.jdbcTemplate.list("eid");
		} else if (key != null && value == null) {
			list = this.jdbcTemplate.list(key);
		} else if (key != null && value != null){
			list = this.jdbcTemplate.list(key, value);
		}

		int count = list.size();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		
		return "employees/employeelist"; ///WEB-INF/views/employees/employeelist.jsp
	}

}
