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
	
	@RequestMapping("/insert")
	public String insert(Employees emp, RedirectAttributes rttr) {

		int result = 0;
		try {
			result = this.jdbcTemplate.jobInsert(emp);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/position/list";
	}
	
	@RequestMapping("/delete")
	public String delete(String job_id, RedirectAttributes rttr) {

		int result = 0;
		try {
			result = this.jdbcTemplate.jobDelete(job_id);
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/position/list";
	}

	
	
	@RequestMapping("/update")
	public String update(Employees emp, RedirectAttributes rttr) {

		int result = 0;
		try {
			result = this.jdbcTemplate.jobUpdate(emp);
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/position/list";
	}
}
