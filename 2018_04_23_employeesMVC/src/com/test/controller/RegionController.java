package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.EmployeesJDBCTemplate;
import com.test.domain.Employees;

@Controller
@RequestMapping("/region")
public class RegionController {

	@Autowired
	private EmployeesJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Employees> list = jdbcTemplate.regionList();
		
		model.addAttribute("list", list);
		
		return "region/regionlist"; ///WEB-INF/views/region/regionlist.jsp
	}
}
