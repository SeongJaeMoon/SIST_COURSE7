package com.test.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Inject
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("list", this.studentService.studentList());
		
		return "list";
	}

}
