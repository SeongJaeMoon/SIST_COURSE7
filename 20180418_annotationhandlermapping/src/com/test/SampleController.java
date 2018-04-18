package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class SampleController {

	@RequestMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("message", "Hello, World!");
		
		return "hello"; ///WEB-INF/views/hello.jsp
	}
	
	@RequestMapping(value="/welcome", method= {RequestMethod.GET, RequestMethod.POST})
	public String welcome(Model model) {
		
		model.addAttribute("message", "Welcome!");
		
		return "welcome"; ///WEB-INF/views/welcome.jsp
	}
	
}
