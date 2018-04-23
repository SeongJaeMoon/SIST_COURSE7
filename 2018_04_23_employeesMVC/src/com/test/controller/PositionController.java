package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.EmployeesJDBCTemplate;
import com.test.domain.Employees;

@Controller
@RequestMapping("/position")
public class PositionController {
	
	@Autowired
	private EmployeesJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Employees> list = jdbcTemplate.jobList();
		
		model.addAttribute("list", list);
		
		return "position/positionlist"; ///WEB-INF/views/position/positionlist.jsp
	}

}
