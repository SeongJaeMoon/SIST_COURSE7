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
	
	
	@RequestMapping("/insert")
	public String insert(String dept_name, RedirectAttributes rttr) {

		int result = 0;
		try {
			result = this.jdbcTemplate.departmentInsert(dept_name);
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/department/list";
	}
	
}
