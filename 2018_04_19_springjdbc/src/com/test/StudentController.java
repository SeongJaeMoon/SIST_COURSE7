package com.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/member")
public class StudentController {
	
	@Autowired
	private StudentJDBCTemplate jdbcTemplate;

	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Student> list = this.jdbcTemplate.studentList();
		
		model.addAttribute("list", list);
		
		return "list";
	}

}
