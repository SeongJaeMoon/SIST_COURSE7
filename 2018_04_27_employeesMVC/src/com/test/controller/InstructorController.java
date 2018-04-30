package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	@RequestMapping("/list")
	public String list() {
		
		return "instructor/list";
	}
}
