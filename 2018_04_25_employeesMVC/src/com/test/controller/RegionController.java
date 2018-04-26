package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/insert")
	public String insert(String reg_name, RedirectAttributes rttr) {

		int result = 0;
		try {
			result = this.jdbcTemplate.regionInsert(reg_name);
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("result:"+result);
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/region/list";
	}
}
