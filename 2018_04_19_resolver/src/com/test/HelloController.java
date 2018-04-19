package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() throws Exception {
		
		/* Spring MVC - Page Redirection Example */
		return "redirect:/test/welcome";
		
	}

	@RequestMapping("/welcome")
	public String welcome(Model model) throws Exception {
		model.addAttribute("message", "Welcome!");
		return "welcome";
	}

}
