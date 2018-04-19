package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class SampleController {

	@RequestMapping("/hello")
	public ModelAndView hello() throws Exception {
		ModelAndView model = new ModelAndView("hello");
		model.addObject("message", "Hello World!");
		return model;
	}

	@RequestMapping("/welcome")
	public String welcome(Model model) throws Exception {
		model.addAttribute("message", "Welcome!");
		return "welcome";
	}
}
